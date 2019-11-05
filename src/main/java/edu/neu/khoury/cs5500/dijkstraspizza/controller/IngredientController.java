package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.IngredientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Api(value = "ingredients", tags = {"ingredient"})
@RestController
@RequestMapping("/ingredients")

public class IngredientController {

  @Autowired
  private IngredientRepository repository;

  /*===== GET Methods =====*/

  @ApiOperation(
      value = "Gets all ingredients in all categories",
      response = Ingredient.class,
      responseContainer = "List",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List getAllIngredients() {
    return repository.findAll();
  }

  @ApiOperation(
      value = "Gets all ingredients of a specific category",
      notes = "Categories are included as a query parameter",
      response = Ingredient.class,
      responseContainer = "List",
      produces = "application/json"
  )
  @RequestMapping(value = "/filter", method = RequestMethod.GET)
  public List getIngredientsByCategory(
      @ApiParam(value = "category type of the ingredients to return", example = "Meat", required = true)
      @RequestParam("category") String category) {
    return repository.findByCategory(category);
  }

  @ApiOperation(
      value = "Gets a specific ingredient by ID",
      response = Ingredient.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Ingredient getIngredientById(
      @ApiParam(value = "ID of the ingredient to return", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Ingredient with id=" + id + " not found.");
    }
    return repository.findById(id).get();
  }

  /*===== POST Methods =====*/

  // TODO: Prevent POST methods from allowing an ID field
  @ApiOperation(
      value = "Creates a new ingredient in the database",
      notes = "ID is assigned by the database and returned to the caller for further reference. Do not include ID in request.",
      response = Ingredient.class,
      consumes = "application/json",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Ingredient newIngredient(
      @ApiParam(value = "JSON ingredient object without an id field", required = true)
      @Valid @RequestBody Ingredient ingredient) {
    repository.save(ingredient);
    return ingredient;
  }

  /*===== PUT Methods =====*/

  @ApiOperation(
      value = "Updates an existing Ingredient based on the ID in the provided object",
      notes = "ID must match an existing ingredient",
      consumes = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.PUT)
  public void updateIngredientById(
      @ApiParam(value = "JSON ingredient object with an existing ingredient ID and updated fields as needed", required = true)
      @Valid @RequestBody Ingredient ingredient) {
    String id = ingredient.getId();
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Ingredient with id=" + id + " not found.");
    }
    repository.save(ingredient);
  }

  /*===== DELETE Methods =====*/

  @ApiOperation(
      value = "Deletes an ingredient from the database based on its ID"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteIngredientById(
      @ApiParam(value = "ID of the ingredient to delete", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Ingredient with id=" + id + " not found.");
    }
    repository.deleteById(id);
  }
}
