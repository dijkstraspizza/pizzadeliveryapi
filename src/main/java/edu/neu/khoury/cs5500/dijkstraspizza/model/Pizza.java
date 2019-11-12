package edu.neu.khoury.cs5500.dijkstraspizza.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A Pizza object with lombok generated boilerplate code (constructor, equals, hashCode, toString,
 * getters, setters).
 */
@Document(collection = "pizzas")
public class Pizza {

  @Id
  private String id;
  private String name;

  private PizzaSize sizeDesc;

  private List<Ingredient> ingredients = new ArrayList<>();

  public Pizza() { }

  public Pizza(PizzaSize size) {
    sizeDesc = size;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PizzaSize getSizeDesc() {
    return sizeDesc;
  }

  public void setSizeDesc(PizzaSize sizeDesc) {
    this.sizeDesc = sizeDesc;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public Double getPrice() {
    return sizeDesc.getValue();
  }
}
