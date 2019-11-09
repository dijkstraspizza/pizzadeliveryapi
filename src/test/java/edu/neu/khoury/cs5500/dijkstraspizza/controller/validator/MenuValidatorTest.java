package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Menu;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaSize;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.IngredientRepository;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class MenuValidatorTest {

  private Pizza meatPizza;
  private Ingredient ham;
  private PizzaSize large;
  private Menu menu;

  @MockBean
  PizzaValidator pizzaValidator;

  @MockBean
  IngredientRepository repository;

  @MockBean
  PizzaRepository pizzaRepository;

  @Autowired
  MenuValidator validator;

  @Before
  public void setUp() throws Exception {
    ham = new Ingredient("ham", "meat", true);
    ham.setId("ham");

    large = PizzaSize.large(18);
    large.setId("large");

    meatPizza = new Pizza();
    meatPizza.setIngredients(Collections.singletonList(ham));
    meatPizza.setSizeDesc(large);

    menu = new Menu();
    menu.setIngredients(new HashSet<>(Collections.singletonList(ham)));
    menu.setPizzas(new HashSet<>(Collections.singletonList(meatPizza)));
  }

  @Configuration
  @Import(MenuValidator.class)
  static class Config {
  }

  @Test
  public void validateValid() {
    when(pizzaValidator.validate(any())).thenReturn(true);
    when(repository.existsById(any())).thenReturn(true);
    when(pizzaRepository.existsById(any())).thenReturn(true);
    assertTrue(validator.validate(menu));
  }

  @Test
  public void validateInvalidPizzas() {
    when(pizzaValidator.validate(any())).thenReturn(false);
    when(pizzaRepository.existsById(any())).thenReturn(true);
    when(repository.existsById(any())).thenReturn(true);
    assertFalse(validator.validate(menu));
  }

  @Test
  public void validateInvalidPizzasRepo() {
    when(pizzaValidator.validate(any())).thenReturn(true);
    when(repository.existsById(any())).thenReturn(true);
    when(pizzaRepository.existsById(any())).thenReturn(false);
    assertFalse(validator.validate(menu));
  }

  @Test
  public void validateInvalidIngredients() {
    when(pizzaValidator.validate(any())).thenReturn(true);
    when(repository.existsById(any())).thenReturn(false);
    when(pizzaRepository.existsById(any())).thenReturn(true);
    assertFalse(validator.validate(menu));
  }
}