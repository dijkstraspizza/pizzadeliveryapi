package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import edu.neu.khoury.cs5500.dijkstraspizza.model.CreditCardInfo;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderValidator implements Validator<Order> {

  @Autowired
  PizzaValidator pizzaValidator;

  @Autowired
  CreditCardValidator cardValidator;

  @Override
  public boolean validate(Order entity) {
    return validateCard(entity.getCardInfo()) && validatePizzas(entity.getPizzas());
  }

  private boolean validateCard(CreditCardInfo cardInfo) {
    if (cardInfo == null) {
      return false;
    }
    return cardValidator.validate(cardInfo);
  }

  private boolean validatePizzas(List<Pizza> pizzas) {

    for (Pizza pizza : pizzas) {
      if (pizza == null) {
        return false;
      }
      if (!pizzaValidator.validate(pizza)) {
        return false;
      }
    }
    return true;
  }
}
