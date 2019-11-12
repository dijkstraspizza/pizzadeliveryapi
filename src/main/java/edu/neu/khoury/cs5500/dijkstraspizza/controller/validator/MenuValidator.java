package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Menu;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.IngredientRepository;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MenuValidator implements Validator<Menu> {

  @Autowired
  PizzaValidator pizzaValidator;

  @Autowired
  IngredientRepository ingredientRepository;

  @Autowired
  PizzaRepository pizzaRepository;

  @Override
  public boolean validate(Menu entity) {
    return validateIngredients(entity.getIngredients()) && validatePizzas(entity.getPizzas());
  }

  private boolean validateIngredients(Set<Ingredient> ingredients) {
    for (Ingredient ingredient : ingredients) {
      if (!ingredientRepository.existsById(ingredient.getId())) {
        return false;
      }
    }
    return true;
  }

  private boolean validatePizzas(Set<Pizza> pizzas) {
    for (Pizza pizza : pizzas) {
      if (!pizzaValidator.validate(pizza)) {
        return false;
      }
      if (!pizzaRepository.existsById(pizza.getId())) {
        return false;
      }
    }
    return true;
  }
}
