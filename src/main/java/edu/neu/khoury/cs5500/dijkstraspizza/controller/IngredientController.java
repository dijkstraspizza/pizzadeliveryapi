package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.IngredientRepository;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

  @Autowired
  private IngredientRepository repository;

  /*===== GET Methods =====*/

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List getAllIngredients() {
    return repository.findAll();
  }

  @RequestMapping(value = "/filter", method = RequestMethod.GET)
  public List getIngredientsByCategory(@RequestParam("category") String category) {
    return repository.findByCategory(category);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Optional<Ingredient> getIngredientById(@PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Ingredient with id=" + id + " not found.");
    }
    return repository.findById(id);
  }

  /*===== POST Methods =====*/

  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Ingredient newIngredient(@Valid @RequestBody Ingredient ingredient) {
    repository.save(ingredient);
    return ingredient;
  }

  /*===== PUT Methods =====*/

  @RequestMapping(value = "/", method = RequestMethod.PUT)
  public void updateIngredientById(
      @Valid @RequestBody Ingredient ingredient) {
    String id = ingredient.getId();
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Ingredient with id=" + id + " not found.");
    }
    repository.save(ingredient);
  }

  /*===== DELETE Methods =====*/

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteIngredientById(@PathVariable("id") String id) {
    repository.deleteById(id);
  }
}
