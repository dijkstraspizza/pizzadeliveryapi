package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import edu.neu.khoury.cs5500.dijkstraspizza.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OrderValidatorTest {

  private Pizza meatPizza;
  private Ingredient ham;
  private PizzaSize large;
  private Order order;

  @MockBean
  PizzaValidator pizzaValidator;

  @Autowired
  OrderValidator orderValidator;

  @Before
  public void setUp() throws Exception {
    ham = new Ingredient("ham", "meat", true);
    ham.setId("ham");

    large = PizzaSize.large(18);
    large.setId("large");

    meatPizza = new Pizza();
    meatPizza.setIngredients(Collections.singletonList(ham));
    meatPizza.setSizeDesc(large);

    Address address = new Address("a", "b", "c", "d");

    order = new Order(address, address);
    order.setPizzas(Collections.singletonList(meatPizza));
  }

  @Configuration
  @Import(OrderValidator.class)
  static class Config {
  }

  @Test
  public void validateValid() {
    when(pizzaValidator.validate(any())).thenReturn(true);
    assertTrue(orderValidator.validate(order));
  }

  @Test
  public void validateInvalid() {
    when(pizzaValidator.validate(any())).thenReturn(false);
    assertFalse(orderValidator.validate(order));
  }
}