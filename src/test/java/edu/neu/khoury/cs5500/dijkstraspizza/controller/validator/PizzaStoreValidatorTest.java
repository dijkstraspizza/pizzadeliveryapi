package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Address;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Menu;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaStore;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.MenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PizzaStoreValidatorTest {

  private Menu menu;
  private PizzaStore pizzaStore;

  @MockBean
  MenuValidator menuValidator;

  @MockBean
  MenuRepository menuRepository;

  @Autowired
  PizzaStoreValidator validator;

  @Before
  public void setUp() throws Exception {
    menu = new Menu();
    menu.setId("id");

    Address address = new Address("1", "2", "3", "4");
    pizzaStore = new PizzaStore(address);
    pizzaStore.setMenu(menu);
  }

  @Configuration
  @Import(PizzaStoreValidator.class)
  static class Config {
  }

  @Test
  public void validateValid() {
    when(menuValidator.validate(any())).thenReturn(true);
    when(menuRepository.existsById(any())).thenReturn(true);
    assertTrue(validator.validate(pizzaStore));
  }

  @Test
  public void validateNewMenu() {
    when(menuValidator.validate(any())).thenReturn(true);
    when(menuRepository.existsById(any())).thenReturn(false);
    assertFalse(validator.validate(pizzaStore));
  }

  @Test
  public void validateInvalidMenu() {
    when(menuValidator.validate(any())).thenReturn(false);
    when(menuRepository.existsById(any())).thenReturn(true);
    assertFalse(validator.validate(pizzaStore));
  }
}