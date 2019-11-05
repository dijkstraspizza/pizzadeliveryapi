package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PriceCalculator;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Collection;
import java.util.List;
import javax.validation.Valid;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Api(value = "pizzas", tags = {"pizza-controller"})
@RestController
@RequestMapping("/pizzas")
public class PizzaController {

  @Autowired
  private PizzaRepository repository;

  /*===== GET Methods =====*/

  @ApiOperation(
      value = "Gets all pizzas in the database",
      response = Pizza.class,
      responseContainer = "List",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List getAllPizzas() {
    return repository.findAll();
  }

  @ApiOperation(
      value = "Gets a specific pizza by ID",
      response = Pizza.class,
      responseContainer = "List",
      produces = "application/json"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Pizza getPizzaById(
      @ApiParam(value = "ID of the pizza to return", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pizza with id=" + id + " not found.");
    }
    return repository.findById(id).get();
  }

  /*===== POST Methods =====*/

  // TODO: Prevent POST methods from allowing an ID field
  @ApiOperation(
      value = "Creates a new pizza in the database",
      notes = "ID is assigned by the database and returned to the caller for further reference. Do not include ID in request.",
      response = Pizza.class,
      consumes = "application/json",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Pizza newPizza(
      @ApiParam(value = "JSON pizza object without an id field", required = true)
      @Valid @RequestBody Pizza pizza) {
    repository.save(pizza);
    return pizza;
  }

  /*===== PUT Methods =====*/

  @ApiOperation(
      value = "Updates an existing pizza based on the ID in the provided object",
      notes = "ID must match an existing pizza",
      consumes = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.PUT)
  public void updatePizzaById(
      @ApiParam(value = "JSON pizza object with an existing ingredient ID and updated fields as needed", required = true)
      @Valid @RequestBody Pizza pizza) {
    String id = pizza.getId();
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pizza with id=" + id + " not found.");
    }
    repository.save(pizza);
  }

  /*===== DELETE Methods =====*/

  @ApiOperation(
      value = "Deletes an pizza from the database based on its ID"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deletePizzaById(
      @ApiParam(value = "ID of the pizza to delete", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pizza with id=" + id + " not found.");
    }
    repository.deleteById(id);
  }
}
