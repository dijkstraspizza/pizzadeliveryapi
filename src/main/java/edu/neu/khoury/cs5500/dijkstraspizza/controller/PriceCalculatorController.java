package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PriceCalculator;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PriceCalculatorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "price-calculators", tags="price-calculator")
@RestController
@RequestMapping("/prices")
public class PriceCalculatorController {

  @Autowired
  private PriceCalculatorRepository repository;

  /*===== GET Methods =====*/

  @ApiOperation(
      value = "Gets the price of an order with a special",
      notes = "Special ID is included as a query parameter",
      response = Double.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/price", method = RequestMethod.GET)
  public @ResponseBody Double getOrderPrice(
      @ApiParam(value = "id of the special to apply", example = "bogoSpecial")
      @RequestParam("special") Optional<String> specialId, @Valid @RequestBody Order order) {
    if (specialId.isEmpty()) {
      PriceCalculator priceCalculator = new PriceCalculator();
      return priceCalculator.calculate(order.getPizzas());
    }
    PriceCalculator priceCalculator = getPriceCalculatorById(specialId.get());
    return priceCalculator.calculate(order.getPizzas());
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
  private PriceCalculator getPriceCalculatorById(String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Price Calculator with id=" + id + " not found."
      );
    }
    return repository.findById(id).get();
  }
}
