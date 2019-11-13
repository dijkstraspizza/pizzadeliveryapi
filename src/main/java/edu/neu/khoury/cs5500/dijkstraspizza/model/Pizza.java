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

  /**
   * Default constructor for Pizza.
   */
  public Pizza() {
  }

  /**
   * Constructor with size.
   *
   * @param size the desired size of the Pizza
   */
  public Pizza(PizzaSize size) {
    sizeDesc = size;
  }

  /**
   * Gets ID number.
   *
   * @return ID number
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the ID number.
   *
   * @param id the new ID
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return pizza name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets size descriptor.
   *
   * @return a size descriptor as a PizzaSize
   */
  public PizzaSize getSizeDesc() {
    return sizeDesc;
  }

  /**
   * Sets the size descriptor.
   *
   * @param sizeDesc a new PizzaSize
   */
  public void setSizeDesc(PizzaSize sizeDesc) {
    this.sizeDesc = sizeDesc;
  }

  /**
   * Gets the ingredients on this pizza.
   *
   * @return a list of pizza ingredients
   */
  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  /**
   * Sets the ingredients list.
   *
   * @param ingredients a new list of Ingredients
   */
  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  /**
   * Gets price of the pizza.
   *
   * @return the price of a pizza
   */
  public Double getPrice() {
    return sizeDesc.getValue();
  }
}
