package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.IngredientRepository;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PizzaValidator implements Validator<Pizza> {

  @Autowired
  IngredientRepository ingredientRepository;

  @Autowired
  PizzaSizeRepository pizzaSizeRepository;

  @Override
  public boolean validate(Pizza entity) {
    return validatePizza(entity);
  }

  private boolean validatePizza(Pizza pizza) {
    return validPizzaSize(pizza) && validIngredients(pizza);
  }

  private boolean validPizzaSize(Pizza pizza) {
    return pizzaSizeRepository.existsById(pizza.getSizeDesc().getId());
  }

  private boolean validIngredients(Pizza pizza) {
    for (Ingredient ingredient : pizza.getIngredients()) {
      if (!ingredientRepository.existsById(ingredient.getId())) {
        return false;
      }
    }
    return true;
  }
}
