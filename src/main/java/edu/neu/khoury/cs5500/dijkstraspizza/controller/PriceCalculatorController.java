package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PriceCalculator;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PriceCalculatorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "price-calculators", tags="price-calculator-controller")
@RestController
@RequestMapping("/prices")
public class PriceCalculatorController {

  @Autowired
  private PriceCalculatorRepository repository;

  @Autowired
  private IngredientController ingredientController;

  @Autowired
  private PizzaController pizzaController;

  /*===== GET Methods =====*/

  @ApiOperation(
      value = "Gets the price of an order with a special",
      notes = "Special ID is included as a query parameter",
      response = Double.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/price", method = RequestMethod.GET)
  public Double getOrderPriceWithSpecial(
      @ApiParam(value = "id of the special to apply", example = "bogoSpecial")
      @RequestParam("special") String specialId, @Valid @RequestBody Order order) {
    PriceCalculator priceCalculator = getPriceCalculatorById(specialId);
    List<Double> pizzaPrices = order.getPizzaIds().stream()
        .map(pizzaController::getPizzaById)
        .map(Pizza::getPrice)
        .collect(Collectors.toList());
    List<List<Ingredient>> ingredients = order.getPizzaIds().stream()
        .map(pizzaController::getPizzaById)
        .map(Pizza::getIngredientIds)
        .map(ingredientController::getIngredientById)
        .collect(Collectors.toList());
    return ;
  }

  @ApiOperation(
      value = "Gets the price of an order",
      response = Order.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/price", method = RequestMethod.GET)
  public Double getOrderPrice(
      @ApiParam(value = "JSON Order object without a price.", required = true)
      @Valid @RequestBody Order order) {
    PriceCalculator priceCalculator = new PriceCalculator();
    return priceCalculator.calculate(order);
  }

  /**
   * @param pizza The pizza for which to calculate a price.
   * @param id The id of the PriceCalculator to retrieve.
   * @return the price of the pizza.
   */
  @ApiOperation(
      value = "Returns the price of a pizza.",
      response = Double.class,
      consumes = "application/json"
  )
  @RequestMapping(value = "/pizza", method = RequestMethod.GET)
  public Double getPizzaPrice(
      @ApiParam(value = "JSON Pizza object", required = true)
      @Valid @RequestBody Pizza pizza, @ApiParam(value = "ID of the price calculator used", required = true)
      @RequestParam("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Price calculator with id=" + id + " not found.");
    }
    List<Ingredient> ingredients = getListOfIngredients(pizza.getIngredientIds());
    return PriceCalculator.calculatePizzaPrice(
        pizza.getPrice(), repository.findById(id).get().getBaseFreeIngredients(), ingredients);
  }

  @ApiOperation(
      value = "Gets all ingredients in all categories",
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

  private List<Ingredient> getListOfIngredients(Collection<String> ingredientIds) {
    return ingredientIds.stream().map(ingredientController::getIngredientById)
        .collect(Collectors.toList());
  }
}
