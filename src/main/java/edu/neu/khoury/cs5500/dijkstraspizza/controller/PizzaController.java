package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaRepository;
import io.swagger.annotations.Api;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Api(value = "pizzas", description = "Provides CRUD operations for Pizza objects")
@RestController
@RequestMapping("/pizzas")
public class PizzaController {

  @Autowired
  private PizzaRepository repository;

  /*===== GET Methods =====*/

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List getAllPizzas() {
    return repository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Pizza getPizzaById(@PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pizza with id=" + id + " not found.");
    }
    return repository.findById(id).get();
  }

  /*===== POST Methods =====*/

  // TODO: Prevent POST methods from allowing an ID field
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Pizza newPizza(@Valid @RequestBody Pizza pizza) {
    repository.save(pizza);
    return pizza;
  }

  /*===== PUT Methods =====*/

  @RequestMapping(value = "/", method = RequestMethod.PUT)
  public void updatePizzaById(
      @Valid @RequestBody Pizza pizza) {
    String id = pizza.getId();
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pizza with id=" + id + " not found.");
    }
    repository.save(pizza);
  }

  /*===== DELETE Methods =====*/

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deletePizzaById(@PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pizza with id=" + id + " not found.");
    }
    repository.deleteById(id);
  }
}
