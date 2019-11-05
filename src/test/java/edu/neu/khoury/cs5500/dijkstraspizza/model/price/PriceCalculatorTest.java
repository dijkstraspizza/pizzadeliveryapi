package edu.neu.khoury.cs5500.dijkstraspizza.model.price;

import edu.neu.khoury.cs5500.dijkstraspizza.controller.PizzaController;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PriceCalculator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

public class PriceCalculatorTest {

  private PriceCalculator genericNoFreeIngredients;
  private PriceCalculator genericTwoFreeIngredients;
  private PriceCalculator halfOffAll;
  private PriceCalculator bogo;
  private Order order;

  @MockBean
  PizzaController pizzaController;

  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);

    order = new Order("add1", "add2");

    genericNoFreeIngredients = new PriceCalculator();
    genericNoFreeIngredients.setId("generic-no-free-price");

    genericTwoFreeIngredients = new PriceCalculator(2);
    genericTwoFreeIngredients.setId("generic-two-free-price");

    halfOffAll = new PriceCalculator(.5);
    halfOffAll.setId("half-off");

    bogo = new PriceCalculator(0, 2, 1, 1.0);
    bogo.setId("bogo");
  }

  @Test
  public void calculatePizzaPrice() {
  }

  @Test
  public void calculate() {
  }
}