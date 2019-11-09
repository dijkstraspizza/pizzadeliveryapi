package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderValidator implements Validator<Order> {

  @Autowired
  PizzaValidator pizzaValidator;

  @Override
  public boolean validate(Order entity) {
    return validatePizzas(entity.getPizzas());
  }

  private boolean validatePizzas(List<Pizza> pizzas) {
    for (Pizza pizza : pizzas) {
      if (!pizzaValidator.validate(pizza)) {
        return false;
      }
    }
    return true;
  }
}
