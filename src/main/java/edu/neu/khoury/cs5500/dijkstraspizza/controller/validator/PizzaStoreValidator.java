package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaStore;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PizzaStoreValidator implements Validator<PizzaStore> {

  @Autowired
  MenuValidator menuValidator;

  @Autowired
  MenuRepository menuRepository;

  @Override
  public boolean validate(PizzaStore entity) {
    return menuValidator.validate(entity.getMenu()) &&
        menuRepository.existsById(entity.getMenu().getId());
  }

}
