package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaSize;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.IngredientRepository;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaSizeRepository;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PizzaValidatorTest {

  private Pizza meatPizza;
  private Ingredient ham;
  private PizzaSize large;

  @MockBean
  private IngredientRepository ingredientRepository;

  @MockBean
  private PizzaSizeRepository pizzaSizeRepository;

  @Autowired
  private PizzaValidator validator;

  @Before
  public void setUp() throws Exception {
    ham = new Ingredient("ham", "meat", true);
    ham.setId("ham");

    large = PizzaSize.large(18);
    large.setId("large");

    meatPizza = new Pizza();
    meatPizza.setIngredients(Collections.singletonList(ham));
    meatPizza.setSizeDesc(large);
  }

  @Test
  public void validateValid() {
    when(ingredientRepository.existsById(any())).thenReturn(true);
    when(pizzaSizeRepository.existsById(any())).thenReturn(true);
    assertTrue(validator.validate(meatPizza));
  }

  @Test
  public void validateInvalidSize() {
    when(ingredientRepository.existsById(any())).thenReturn(true);
    when(pizzaSizeRepository.existsById(any())).thenReturn(false);
    assertFalse(validator.validate(meatPizza));
  }

  @Test
  public void validateInvalidIngredient() {
    when(ingredientRepository.existsById(any())).thenReturn(false);
    when(pizzaSizeRepository.existsById(any())).thenReturn(true);
    assertFalse(validator.validate(meatPizza));
  }

  @Configuration
  @Import(PizzaValidator.class)
  static class Config {

  }
}