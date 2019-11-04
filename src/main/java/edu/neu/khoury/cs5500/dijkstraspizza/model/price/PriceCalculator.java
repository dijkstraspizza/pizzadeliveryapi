package edu.neu.khoury.cs5500.dijkstraspizza.model.price;

import edu.neu.khoury.cs5500.dijkstraspizza.controller.IngredientController;
import edu.neu.khoury.cs5500.dijkstraspizza.controller.PizzaController;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "price-calculators")
@Data
public class PriceCalculator implements IPriceCalculator{

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

  public static Double calculatePizzaPrice(Pizza pizza, Integer baseFreeIngredients) {
    double price = pizza.getPrice();
    IngredientController ingredientController = new IngredientController();
    List<Ingredient> ingredients = pizza.getIngredientIds()
        .stream().map(ingredientController::getIngredientById)
        .collect(Collectors.toList());
    for (int i = baseFreeIngredients; i < ingredients.size(); i++) {
      price += ingredients.get(i).getPrice();
    }
    return price;
  }

  private Double calculateBasePrice(Order order) {
    double price = 0;
    PizzaController pizzaController = new PizzaController();
    List<Pizza> pizzas = order.getPizzaIds().stream()
        .map(pizzaController::getPizzaById)
        .sorted(Pizza::compareTo).collect(Collectors.toList());
    for (int i = 0; i < pizzas.size(); i++) {
      price += calculatePizzaPrice(pizzas.get(i), baseFreeIngredients);
    }
    return price;
  }

  @Id
  private String id;

  @Override
  public Double calculate(Order order) {
    double price = calculateBasePrice(order);
    if (order.getPizzaIds().size() >= requiredPizzas) {
      if (pizzasAppliedTo < 0) {
        return price - price * discountRatio;
      }
      PizzaController pizzaController = new PizzaController();
      List<Pizza> pizzas = order.getPizzaIds().stream()
          .map(pizzaController::getPizzaById)
          .sorted(Pizza::compareTo).collect(Collectors.toList());
      for (int i = 0; i < pizzasAppliedTo; i++) {
        price += pizzas.get(i).getPrice() - discountRatio * pizzas.get(i).getPrice();
      }
      for (int i = pizzasAppliedTo; i < pizzas.size(); i++) {
        price += pizzas.get(i).getPrice();
      }
    }
    return price;
  }
}
