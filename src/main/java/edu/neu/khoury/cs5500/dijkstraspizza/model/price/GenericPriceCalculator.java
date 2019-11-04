package edu.neu.khoury.cs5500.dijkstraspizza.model.price;

import edu.neu.khoury.cs5500.dijkstraspizza.controller.PizzaController;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that calcualates the price of a pizza.
 */
@Document(collection = "generic-price-calculators")
public class GenericPriceCalculator implements IPriceCalculator {

  @Id
  private String id;
  private Integer baseFreeIngredients;

  @Autowired
  private PizzaController pizzaController;

  /**
   * Constructor for a price calculator where no ingredients are free.
   */
  public GenericPriceCalculator() {
    this.baseFreeIngredients = 0;
  }

  public GenericPriceCalculator(Integer baseFreeIngredients) {
    this.baseFreeIngredients = baseFreeIngredients;
  }

  public String getId() {
    return id;
  }

  public Integer getBaseFreeIngredients() {
    return baseFreeIngredients;
  }

  /**
   * @param order An order.
   * @return The order with the price calculated.
   */
  @Override
  public Order calculate(Order order) {
    double price = 0;
    List<Pizza> pizzas = order.getPizzaIds().stream()
        .map(pizzaId -> pizzaController.getPizzaById(pizzaId))
        .sorted(Pizza::compareTo).collect(Collectors.toList());
    for (int i = baseFreeIngredients; i < pizzas.size(); i++) {
      price += pizzas.get(i).getPrice();
    }
    order.setPrice(price);
    return order;
  }
}
