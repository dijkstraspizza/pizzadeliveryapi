package edu.neu.khoury.cs5500.dijkstraspizza.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

  public PizzaSize(double inches, String description, double value) {
    this.description = description;
    this.inches = inches;
    this.value = value;
  }

  public static PizzaSize small(double inches) {
    return new PizzaSize(inches, SMALL_DESCRIPTION, SMALL);
  }

  public static PizzaSize small(int inches) {
    return new PizzaSize(inches, SMALL_DESCRIPTION, SMALL);
  }

  public static PizzaSize medium(double inches) {
    return new PizzaSize(inches, MEDIUM_DESCRIPTION, MEDIUM);
  }

  public static PizzaSize medium(int inches) {
    return new PizzaSize(inches, MEDIUM_DESCRIPTION, MEDIUM);
  }

  public static PizzaSize large(double inches) {
    return new PizzaSize(inches, LARGE_DESCRIPTION, LARGE);
  }

  public static PizzaSize large(int inches) {
    return new PizzaSize(inches, LARGE_DESCRIPTION, LARGE);
  }

  public double getInches() {
    return inches;
  }

  public String getDescription() {
    return description;
  }

  public double getValue() {
    return value;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setInches(double inches) {
    this.inches = inches;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
