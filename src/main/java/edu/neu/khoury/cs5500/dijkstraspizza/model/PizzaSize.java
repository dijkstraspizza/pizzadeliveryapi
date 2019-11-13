package edu.neu.khoury.cs5500.dijkstraspizza.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * PizzaSize represents the size of a pizza through a description, size (inches) and price.
 */
@Document(collection = "sizes")
public class PizzaSize {

  private static final double SMALL = 8.0;
  private static final double MEDIUM = 10.0;
  private static final double LARGE = 12.0;
  private static final String SMALL_DESCRIPTION = "small";
  private static final String MEDIUM_DESCRIPTION = "medium";
  private static final String LARGE_DESCRIPTION = "large";

  @Id
  private String id;
  private double inches;
  private String description;
  private double value;

  /**
   * Standard constructor with fields.
   *
   * @param inches      size in inches
   * @param description a string description (i.e. "Supreme")
   * @param value       the price of this pizza
   */
  public PizzaSize(double inches, String description, double value) {
    this.description = description;
    this.inches = inches;
    this.value = value;
  }

  /**
   * Creates a small pizza.
   *
   * @param inches size in inches
   * @return a new small pizza
   */
  public static PizzaSize small(double inches) {
    return new PizzaSize(inches, SMALL_DESCRIPTION, SMALL);
  }

  /**
   * Creates a small pizza.
   *
   * @param inches size in inches
   * @return a new small pizza
   */
  public static PizzaSize small(int inches) {
    return new PizzaSize(inches, SMALL_DESCRIPTION, SMALL);
  }

  /**
   * Creates a medium pizza.
   *
   * @param inches size in inches
   * @return a new medium pizza
   */
  public static PizzaSize medium(double inches) {
    return new PizzaSize(inches, MEDIUM_DESCRIPTION, MEDIUM);
  }

  /**
   * Creates a medium pizza.
   *
   * @param inches size in inches
   * @return a new medium pizza
   */
  public static PizzaSize medium(int inches) {
    return new PizzaSize(inches, MEDIUM_DESCRIPTION, MEDIUM);
  }

  /**
   * Creates a large pizza.
   *
   * @param inches size in inches
   * @return a new large pizza
   */
  public static PizzaSize large(double inches) {
    return new PizzaSize(inches, LARGE_DESCRIPTION, LARGE);
  }

  /**
   * Creates a large pizza.
   *
   * @param inches size in inches
   * @return a new large pizza
   */
  public static PizzaSize large(int inches) {
    return new PizzaSize(inches, LARGE_DESCRIPTION, LARGE);
  }

  /**
   * Gets pizza size.
   *
   * @return the size of the pizza
   */
  public double getInches() {
    return inches;
  }

  /**
   * Sets pizza size.
   *
   * @param inches new size in inches
   */
  public void setInches(double inches) {
    this.inches = inches;
  }

  /**
   * Gets description of pizza.
   *
   * @return pizza description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets description of pizza.
   *
   * @param description new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets price of the pizza.
   *
   * @return the price
   */
  public double getValue() {
    return value;
  }

  /**
   * Sets price of the pizza.
   *
   * @param value the new price
   */
  public void setValue(double value) {
    this.value = value;
  }

  /**
   * Gets the pizza ID.
   *
   * @return the ID
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the pizza ID.
   *
   * @param id the new ID
   */
  public void setId(String id) {
    this.id = id;
  }
}
