package edu.neu.khoury.cs5500.dijkstraspizza.model;

import edu.neu.khoury.cs5500.dijkstraspizza.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

public class PriceCalculatorTest {

  private PriceCalculator genericNoFreeIngredients;
  private PriceCalculator halfOffAll;
  private PriceCalculator bogo;
  private PriceCalculator buyOneGetTwoFree;
  private PriceCalculator buyTwoGetOneHalfOff;
  private PriceCalculator buyThreeGet25OffAll;
  private Pizza cheesePizza;
  private Pizza pepperoniPizza;
  private Pizza hugePizza;

  private Order order;

  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);

    Address store = new Address("123", "Seattle", "WA", "98103");
    Address customer = new Address("abc", "Seattle", "WA", "98117");

    cheesePizza = new Pizza(PizzaSize.small(8));

    pepperoniPizza = new Pizza(PizzaSize.medium(10));

    hugePizza = new Pizza(PizzaSize.large(12));
    hugePizza.setIngredients(Arrays.asList(new Ingredient("mushrooms", "vegetable", true),
        new Ingredient("ham", "meat", true)));

    order = new Order(store, customer);
    order.setPizzas(Arrays.asList(pepperoniPizza, cheesePizza, hugePizza));

    genericNoFreeIngredients = new PriceCalculator();
    genericNoFreeIngredients.setId("generic-price");

    halfOffAll = new PriceCalculator(.5, "halfOff");
    halfOffAll.setId("half-off");

    bogo = new PriceCalculator(2, 1, 1.0, "bogo");
    bogo.setId("bogo");

    buyOneGetTwoFree = new PriceCalculator(3, 2, 1.0, "buyOneGetTwo");
    buyOneGetTwoFree.setId("buy-one-get-two");

    buyTwoGetOneHalfOff = new PriceCalculator(3, 1, .5, "buyTwoGetHalf");
    buyTwoGetOneHalfOff.setId("buy-two-get-half");

    buyThreeGet25OffAll = new PriceCalculator(3, -1, .25, "buyThreeGet25");
  }

  @Test
  public void calculateGeneric() {
    assertEquals(32, genericNoFreeIngredients.calculate(order.getPizzas()), 0);
  }

  @Test
  public void calculateHalfOffAll() {
    assertEquals(16, halfOffAll.calculate(order.getPizzas()), 0);
  }

  @Test
  public void calculateBogo() {
    assertEquals(24, bogo.calculate(order.getPizzas()), 0);
  }

  @Test
  public void calculateBogoOnlyOne() {
    order.setPizzas(Collections.singletonList(cheesePizza));
    assertEquals(cheesePizza.getPrice(), bogo.calculate(order.getPizzas()), 0);
  }

  @Test
  public void calculateBuyOneGetTwo() {
    assertEquals(14, buyOneGetTwoFree.calculate(order.getPizzas()), 0);
  }

  @Test
  public void calculateBuyOneGetTwoOnlyTwo() {
    order.setPizzas(Arrays.asList(cheesePizza, pepperoniPizza));
    assertEquals(18, buyOneGetTwoFree.calculate(order.getPizzas()), 0);
  }

  @Test
  public void calculateTwoGetHalf() {
    assertEquals(28, buyTwoGetOneHalfOff.calculate(order.getPizzas()), 0);
  }

  @Test
  public void calculateBuyThreeGet25Off() {
    assertEquals(32 * .75,
        buyThreeGet25OffAll.calculate(order.getPizzas()), 0);
  }

  @Test
  public void calculateBuyThreeGet25OffNotSatisfied() {
    order.setPizzas(Arrays.asList(pepperoniPizza, hugePizza));
    assertEquals(24, buyThreeGet25OffAll.calculate(order.getPizzas()), 0);
  }
}