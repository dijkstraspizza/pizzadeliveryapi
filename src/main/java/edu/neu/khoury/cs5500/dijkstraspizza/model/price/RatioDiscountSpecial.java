package edu.neu.khoury.cs5500.dijkstraspizza.model.price;

import edu.neu.khoury.cs5500.dijkstraspizza.controller.PizzaController;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Document(collection = "ratio-discount-specials")
public class RatioDiscountSpecial extends GenericPriceCalculator {

  private Double discountRatio;
  private Integer requiredPizzas;
  private Optional<Integer> pizzasAppliedTo;

  @Autowired
  PizzaController pizzaController;

  public RatioDiscountSpecial(Double discountRatio) {
    super();
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = Optional.empty();
    this.discountRatio = discountRatio;
  }

  public RatioDiscountSpecial(Integer baseFreeIngredients, Double discountRatio) {
    super(baseFreeIngredients);
    this.discountRatio = discountRatio;
    this.requiredPizzas = 0;
    this.pizzasAppliedTo = Optional.empty();
  }

  public RatioDiscountSpecial(Integer baseFreeIngredients, Integer requiredPizzas,
                              Integer pizzasAppliedTo, Double discountRatio) {
    super(baseFreeIngredients);
    this.requiredPizzas = requiredPizzas;
    this.pizzasAppliedTo = Optional.of(pizzasAppliedTo);
    this.discountRatio = discountRatio;
  }

  @Override
  public Order calculate(Order order) {
    order = super.calculate(order);
    if (order.getPizzaIds().size() >= requiredPizzas) {
      if (pizzasAppliedTo.isEmpty()) {
        order.setPrice(order.getPrice() - discountRatio * order.getPrice());
      } else {
        double price = 0;
        List<Pizza> pizzas = order.getPizzaIds().stream()
            .map(pizzaId -> pizzaController.getPizzaById(pizzaId))
            .sorted(Pizza::compareTo).collect(Collectors.toList());
        for (int i = 0; i < pizzasAppliedTo.get(); i++) {
          price += pizzas.get(i).getPrice() - pizzas.get(i).getPrice();
        }
        for (int i = pizzasAppliedTo.get(); i < pizzas.size(); i++) {
          price += pizzas.get(i).getPrice();
        }
        order.setPrice(price);
      }
    }
    return order;
  }
}
