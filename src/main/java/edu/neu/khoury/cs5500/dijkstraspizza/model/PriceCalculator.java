package edu.neu.khoury.cs5500.dijkstraspizza.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * PriceCalculator determines the price of a pizza or full order of pizzas.
 */
@Document(collection = "price-calculators")
@Data
public class PriceCalculator {

  private static final int DEFAULT_FREE_INGREDIENTS = 1;
  private static final double DEFAULT_INGREDIENT_COST = 2;
  private static final double SEATTLE_SALES_TAX = .101;

  @Id
  private String id;
  private Double discountRatio;
  private Integer requiredPizzas;
  private Integer pizzasAppliedTo;
  private String name;
  private Integer freeIngredients;
  private Double ingredientCost;

  /**
   * Default constructor for PriceCalculator.
   */
  public PriceCalculator() {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = 0.0;
    this.name = "generic";
    this.freeIngredients = DEFAULT_FREE_INGREDIENTS;
    this.ingredientCost = DEFAULT_INGREDIENT_COST;
  }

  /**
   * Constructor with ingredient and name info.
   *
   * @param freeIngredients the number of free ingredients
   * @param ingredientCost  the cost of one ingredient
   * @param name            the name
   */
  public PriceCalculator(Integer freeIngredients, Double ingredientCost, String name) {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = 0.0;
    this.name = name;
    this.freeIngredients = freeIngredients;
    this.ingredientCost = ingredientCost;
  }

  /**
   * Constructor with discount and name info.
   *
   * @param discountRatio the discount ratio
   * @param name          the name
   */
  public PriceCalculator(Double discountRatio, String name) {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = discountRatio;
    this.name = name;
    this.freeIngredients = DEFAULT_FREE_INGREDIENTS;
    this.ingredientCost = DEFAULT_INGREDIENT_COST;
  }

  /**
   * Constructor with ingredient, discount and name info.
   *
   * @param freeIngredients number of free ingredients
   * @param ingredientCost  cost of one ingredient
   * @param discountRatio   the discount ratio
   * @param name            the name
   */
  public PriceCalculator(Integer freeIngredients, Double ingredientCost,
      Double discountRatio, String name) {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = discountRatio;
    this.name = name;
    this.freeIngredients = freeIngredients;
    this.ingredientCost = ingredientCost;
  }

  /**
   * Constructor with Pizza info and name.
   *
   * @param requiredPizzas  number of required pizzas
   * @param pizzasAppliedTo how many pizzas the discount is applied to
   * @param discountRatio   the discount ratio
   * @param name            the name
   */
  public PriceCalculator(Integer requiredPizzas, Integer pizzasAppliedTo, Double discountRatio,
      String name) {
    this.requiredPizzas = requiredPizzas;
    this.pizzasAppliedTo = pizzasAppliedTo;
    this.discountRatio = discountRatio;
    this.name = name;
    this.freeIngredients = DEFAULT_FREE_INGREDIENTS;
    this.ingredientCost = DEFAULT_INGREDIENT_COST;
  }

  /**
   * Full constructor.
   *
   * @param freeIngredients number of free ingredients
   * @param ingredientCost  the cost of one ingredient
   * @param requiredPizzas  the number of required pizzas
   * @param pizzasAppliedTo the number of pizzas that get the discount
   * @param discountRatio   the discount ratio
   * @param name            the name
   */
  public PriceCalculator(Integer freeIngredients, Double ingredientCost,
      Integer requiredPizzas, Integer pizzasAppliedTo, Double discountRatio,
      String name) {
    this.requiredPizzas = requiredPizzas;
    this.pizzasAppliedTo = pizzasAppliedTo;
    this.discountRatio = discountRatio;
    this.name = name;
    this.freeIngredients = freeIngredients;
    this.ingredientCost = ingredientCost;
  }

  /**
   * Calculates the price of a pizza.
   *
   * @param size        size of the pizza
   * @param numToppings the number of toppings on the pizza
   * @return the price
   */
  public static double calculatePizzaPrice(PizzaSize size, Integer numToppings) {
    if (numToppings <= DEFAULT_FREE_INGREDIENTS) {
      return size.getValue();
    }
    return size.getValue() + (numToppings - DEFAULT_FREE_INGREDIENTS) * DEFAULT_INGREDIENT_COST;
  }

  /**
   * Calculates the price of a pizza.
   *
   * @param pizza a pizza
   * @return the price
   */
  private Double calculatePizzaPrice(Pizza pizza) {
    double price = pizza.getPrice();
    for (int i = freeIngredients; i < pizza.getIngredients().size(); i++) {
      price += ingredientCost;
    }
    return price;
  }

  /**
   * Calculates the base price of an order.
   *
   * @param pizzas a list of pizzas
   * @return the price
   */
  private Double calculateBasePrice(List<Pizza> pizzas) {
    return pizzas.stream().mapToDouble(this::calculatePizzaPrice).sum();
  }

  /**
   * Calculates the tax of an order.
   *
   * @param noTaxPrice the pre-tax price
   * @return the final price
   */
  public Double calculateTax(Double noTaxPrice) {
    return SEATTLE_SALES_TAX * noTaxPrice;
  }

  /**
   * Calculates a discount for an order
   *
   * @param order the order
   * @return the discount price
   */
  public Double calculateDiscount(Order order) {
    if (order.getPizzas().size() < requiredPizzas) {
      return 0.0;
    }
    if (pizzasAppliedTo < 0) { // -1 represents all pizzas
      double price = calculateBasePrice(order.getPizzas());
      return price * discountRatio;
    }
    List<Pizza> sortedPizzas = new ArrayList<>(order.getPizzas());
    sortedPizzas.sort(Comparator.comparing(this::calculatePizzaPrice));
    double price = 0;
    for (int i = 0; i < pizzasAppliedTo; i++) {
      price += discountRatio * calculatePizzaPrice(sortedPizzas.get(i));
    }
    return price;
  }

  /**
   * Calculate the price without tax, including discounts.
   *
   * @param pizzas a list of pizzas
   * @return the pre-tax price
   */
  public Double calculateNoTaxPrice(List<Pizza> pizzas) {
    if (pizzas.size() < requiredPizzas) {
      return calculateBasePrice(pizzas);
    }
    if (pizzasAppliedTo < 0) { // -1 represents all pizzas
      double price = calculateBasePrice(pizzas);
      return (price - price * discountRatio);
    }
    List<Pizza> sortedPizzas = new ArrayList<>(pizzas);
    sortedPizzas.sort(Comparator.comparing(this::calculatePizzaPrice));
    double price = 0;
    for (int i = 0; i < pizzasAppliedTo; i++) {
      price += calculatePizzaPrice(sortedPizzas.get(i)) -
          discountRatio * calculatePizzaPrice(sortedPizzas.get(i));
    }
    for (int i = pizzasAppliedTo; i < pizzas.size(); i++) {
      price += calculatePizzaPrice(sortedPizzas.get(i));
    }
    return price;
  }

  /**
   * Calculates the full price of an order.
   *
   * @param pizzas a list of pizzas
   * @return the final price
   */
  public Double calculatePrice(List<Pizza> pizzas) {
    double price = calculateNoTaxPrice(pizzas);
    return price + calculateTax(price);
  }
}
