package edu.neu.khoury.cs5500.dijkstraspizza.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "price-calculators")
@Data
public class PriceCalculator {

  private Double discountRatio;
  private Integer requiredPizzas;
  private Integer baseFreeIngredients;
  private Integer pizzasAppliedTo;

  public PriceCalculator() {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = 0.0;
    this.baseFreeIngredients = 0;
  }

  public PriceCalculator(Integer baseFreeIngredients) {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = 0.0;
    this.baseFreeIngredients = baseFreeIngredients;
  }

  public PriceCalculator(Double discountRatio) {
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.discountRatio = discountRatio;
    this.baseFreeIngredients = 0;
  }

  public PriceCalculator(Integer baseFreeIngredients, Double discountRatio) {
    this.discountRatio = discountRatio;
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = -1;
    this.baseFreeIngredients = baseFreeIngredients;
  }

  public PriceCalculator(Integer baseFreeIngredients, Integer requiredPizzas,
                              Integer pizzasAppliedTo, Double discountRatio) {
    this.requiredPizzas = requiredPizzas;
    this.pizzasAppliedTo = pizzasAppliedTo;
    this.discountRatio = discountRatio;
    this.baseFreeIngredients = baseFreeIngredients;
  }

  public static Double calculatePizzaPrice(Double pizzaPrice,
                                           Integer baseFreeIngredients,
                                           List<Ingredient> ingredients) {
    double price = pizzaPrice;
    for (int i = baseFreeIngredients; i < ingredients.size(); i++) {
      price += ingredients.get(i).getPrice();
    }
    return price;
  }

  private Double calculateBasePrice(List<Double> pizzaPrices,
                                    List<List<Ingredient>> pizzaIngredients) {
    double price = 0;
    for (int i = 0; i < pizzaPrices.size(); i++) {
      price += calculatePizzaPrice(pizzaPrices.get(i), baseFreeIngredients, pizzaIngredients.get(i));
    }
    return price;
  }

  @Id
  private String id;

  public Double calculate(List<Double> pizzaPrices,
                          List<List<Ingredient>> pizzaIngredients) {
    if (pizzaPrices.size() < requiredPizzas) {
      return calculateBasePrice(pizzaPrices, pizzaIngredients);
    }
    if (pizzasAppliedTo < 0) {
      double price = calculateBasePrice(pizzaPrices, pizzaIngredients);
      return price - price * discountRatio;
    }
    double price = 0;
    double pizzaPrice;
    for (int i = 0; i < pizzasAppliedTo; i++) {
      pizzaPrice = calculatePizzaPrice(pizzaPrices.get(i), baseFreeIngredients,
          pizzaIngredients.get(i));
      price += pizzaPrice - discountRatio * pizzaPrice;
    }
    for (int i = pizzasAppliedTo; i < pizzaPrices.size(); i++) {
      price += calculatePizzaPrice(pizzaPrices.get(i),
          baseFreeIngredients, pizzaIngredients.get(i));
    }
    return price;
  }
}
