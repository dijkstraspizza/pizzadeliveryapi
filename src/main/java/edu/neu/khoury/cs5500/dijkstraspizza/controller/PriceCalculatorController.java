package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaSize;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Price;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PriceCalculator;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaSizeRepository;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PriceCalculatorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Api(value = "price-calculators", tags = {"price-calculator"})
@RestController
@RequestMapping("/prices")
public class PriceCalculatorController {

  @Autowired
  private PriceCalculatorRepository repository;

  @Autowired
  private PizzaController pizzaController;

  @Autowired
  private PizzaSizeRepository pizzaSizeRepository;

  /*===== GET Methods =====*/

  @ApiOperation(
      value = "Gets the price of a pizza, based on size and number of toppings.",
      response = Price.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/price/pizza", method = RequestMethod.GET)
  public @ResponseBody
  Price getPizzaPrice(
      @ApiParam(value = "ID of the pizza size", example = "smallId")
      @RequestParam("size") String sizeId,
      @ApiParam(value = "Number of toppings on the pizza", example = "2")
      @RequestParam(value = "num-toppings") Integer numToppings) {
    if (!pizzaSizeRepository.existsById(sizeId)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Size ID" + sizeId + " does not exist."
      );
    }
    PizzaSize size = pizzaSizeRepository.findById(sizeId).get();
    return new Price(PriceCalculator.calculatePizzaPrice(size, numToppings));
  }

  @ApiOperation(
      value = "Gets the price of an order.",
      notes = "Special ID is included as a query parameter",
      response = Price.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/price", method = RequestMethod.GET)
  public @ResponseBody
  Price getOrderPrice(
      @ApiParam(value = "id of the special to apply", example = "bogoSpecial")
      @RequestParam("special") Optional<String> specialId,
      @ApiParam(value = "Id of Pizza(s) in the order", example = "cheesePizzaId")
      @RequestParam(value = "pizzaId[]") String[] pizzaIds) {
    List<Pizza> pizzas = new ArrayList<>();
    for (String id : pizzaIds) {
      pizzas.add(pizzaController.getPizzaById(id));
    }
    if (specialId.isEmpty()) {
      PriceCalculator priceCalculator = new PriceCalculator();
      return new Price(priceCalculator.calculatePrice(pizzas));
    }
    PriceCalculator priceCalculator = getPriceCalculatorById(specialId.get());
    return new Price(priceCalculator.calculatePrice(pizzas));
  }

  @ApiOperation(
      value = "Gets all price calculators.",
      response = PriceCalculator.class,
      responseContainer = "List",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List getAllPriceCalculators() {
    return repository.findAll();
  }

  @ApiOperation(
      value = "Gets a specific price calculator by ID",
      response = PriceCalculator.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public PriceCalculator getPriceCalculatorByIdHttp(
      @ApiParam(value = "ID of the price calculator to return", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Price calculator with id=" + id + " not found.");
    }
    return repository.findById(id).get();
  }

  /*===== POST Methods =====*/

  // TODO: Prevent POST methods from allowing an ID field
  @ApiOperation(
      value = "Creates a new price calculator in the database",
      notes = "ID is assigned by the database and returned to the caller for further reference. Do not include ID in request.",
      response = PriceCalculator.class,
      consumes = "application/json",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public PriceCalculator newPriceCalculator(
      @ApiParam(value = "JSON Price Calculator object without an id field", required = true)
      @Valid @RequestBody PriceCalculator priceCalculator) {
    repository.save(priceCalculator);
    return priceCalculator;
  }

  /*===== DELETE Methods =====*/

  @ApiOperation(
      value = "Deletes a price calculator from the database based on its ID"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deletePriceCalculatorById(
      @ApiParam(value = "ID of the price calculator to delete", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Price calculator with id=" + id + " not found.");
    }
    repository.deleteById(id);
  }

  /*===== Non-Http Methods =====*/

  public PriceCalculator getPriceCalculatorById(String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Price Calculator with id=" + id + " not found."
      );
    }
    return repository.findById(id).get();
  }

  Double getOrderPrice(Optional<String> specialId, Order order) {
    if (specialId.isEmpty()) {
      PriceCalculator priceCalculator = new PriceCalculator();
      return priceCalculator.calculatePrice(order.getPizzas());
    }
    PriceCalculator priceCalculator = getPriceCalculatorById(specialId.get());
    return priceCalculator.calculatePrice(order.getPizzas());
  }
}
