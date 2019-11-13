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

  public PriceCalculator() {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = 0.0;
    this.name = "generic";
    this.freeIngredients = DEFAULT_FREE_INGREDIENTS;
    this.ingredientCost = DEFAULT_INGREDIENT_COST;
  }

  public PriceCalculator(Integer freeIngredients, Double ingredientCost, String name) {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = 0.0;
    this.name = name;
    this.freeIngredients = freeIngredients;
    this.ingredientCost = ingredientCost;
  }

  public PriceCalculator(Double discountRatio, String name) {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = discountRatio;
    this.name = name;
    this.freeIngredients = DEFAULT_FREE_INGREDIENTS;
    this.ingredientCost = DEFAULT_INGREDIENT_COST;
  }

  public PriceCalculator(Integer freeIngredients, Double ingredientCost,
      Double discountRatio, String name) {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = discountRatio;
    this.name = name;
    this.freeIngredients = freeIngredients;
    this.ingredientCost = ingredientCost;
  }

  public PriceCalculator(Integer requiredPizzas, Integer pizzasAppliedTo, Double discountRatio,
      String name) {
    this.requiredPizzas = requiredPizzas;
    this.pizzasAppliedTo = pizzasAppliedTo;
    this.discountRatio = discountRatio;
    this.name = name;
    this.freeIngredients = DEFAULT_FREE_INGREDIENTS;
    this.ingredientCost = DEFAULT_INGREDIENT_COST;
  }

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

  public static double calculatePizzaPrice(PizzaSize size, Integer numToppings) {
    if (numToppings <= DEFAULT_FREE_INGREDIENTS) {
      return size.getValue();
    }
    return size.getValue() + (numToppings - DEFAULT_FREE_INGREDIENTS) * DEFAULT_INGREDIENT_COST;
  }

  private Double calculatePizzaPrice(Pizza pizza) {
    double price = pizza.getPrice();
    for (int i = freeIngredients; i < pizza.getIngredients().size(); i++) {
      price += ingredientCost;
    }
    return price;
  }

  private Double calculateBasePrice(List<Pizza> pizzas) {
    return pizzas.stream().mapToDouble(this::calculatePizzaPrice).sum();
  }

  public Double calculateTax(Double noTaxPrice) {
    return SEATTLE_SALES_TAX * noTaxPrice;
  }

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

  public Double calculatePrice(List<Pizza> pizzas) {
    double price = calculateNoTaxPrice(pizzas);
    return price + calculateTax(price);
  }
}
