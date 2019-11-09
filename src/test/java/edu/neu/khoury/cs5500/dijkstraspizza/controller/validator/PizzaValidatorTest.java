package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaSize;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.IngredientRepository;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaSizeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PizzaValidatorTest {

  private Pizza meatPizza;

  @Autowired
  private Validator<Pizza> validator;

  @MockBean
  private IngredientRepository ingredientRepository;
  private PizzaSizeRepository pizzaSizeRepository;

  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);

    meatPizza = new Pizza();

    validator = new PizzaValidator();
  }

  @Test
  public void validateValid() {
  }
}