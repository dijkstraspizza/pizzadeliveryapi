package edu.neu.khoury.cs5500.dijkstraspizza.controller;


import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaSize;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaSizeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Api(value = "pizza-sizes", tags = {"pizza-size"})
@RestController
@RequestMapping("/sizes")
public class PizzaSizeController {

  @Autowired
  PizzaSizeRepository repository;

  /*===== GET Methods =====*/

  @ApiOperation(
      value = "Gets all pizza sizes.",
      response = PizzaSize.class,
      responseContainer = "List",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List getAllPizzaSizes() {
    return repository.findAll();
  }

  @ApiOperation(
      value = "Gets a specific pizza size by ID",
      response = PizzaSize.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public PizzaSize getPizzaSizeById(
      @ApiParam(value = "ID of the pizza size to return", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pizza size with id=" + id + " not found.");
    }
    return repository.findById(id).get();
  }

  /*===== POST Methods =====*/

  // TODO: Prevent POST methods from allowing an ID field
  @ApiOperation(
      value = "Creates a new pizza size in the database",
      notes = "ID is assigned by the database and returned to the caller for further reference. Do not include ID in request.",
      response = PizzaSize.class,
      consumes = "application/json",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public PizzaSize newPizzaSize(
      @ApiParam(value = "JSON pizza size object without an id field", required = true)
      @Valid @RequestBody PizzaSize size) {
    repository.save(size);
    return size;
  }

  /*===== DELETE Methods =====*/

  @ApiOperation(
      value = "Deletes a pizza size from the database based on its ID"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deletePizzaSizeById(
      @ApiParam(value = "ID of the pizza size to delete", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pizza size with id=" + id + " not found.");
    }
    repository.deleteById(id);
  }

}
