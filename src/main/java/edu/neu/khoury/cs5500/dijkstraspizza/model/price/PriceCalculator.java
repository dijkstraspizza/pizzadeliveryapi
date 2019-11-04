package edu.neu.khoury.cs5500.dijkstraspizza.model.price;

import edu.neu.khoury.cs5500.dijkstraspizza.controller.PizzaController;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "price-calculators")
@Data
public abstract class PriceCalculator implements IPriceCalculator{

  @Id
  private String id;
  private Integer baseFreeIngredients;

  @Autowired
  private PizzaController pizzaController;

}
