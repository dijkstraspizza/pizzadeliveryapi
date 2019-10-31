package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaRepository;
import java.util.List;
import java.util.Optional;
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
  public Optional<Pizza> getPizzaById(@PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Pizza with id=" + id + " not found.");
    }
    return repository.findById(id);
  }

  /*===== POST Methods =====*/

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
    repository.deleteById(id);
  }
}
