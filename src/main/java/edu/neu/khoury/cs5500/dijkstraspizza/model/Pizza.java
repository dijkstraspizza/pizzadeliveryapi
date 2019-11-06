package edu.neu.khoury.cs5500.dijkstraspizza.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A Pizza object with lombok generated boilerplate code (constructor, equals, hashCode, toString,
 * getters, setters).
 */
@Document(collection = "pizzas")
@Data
public class Pizza implements Comparable<Pizza>{

  @Id
  private String id;
  private String name;
  private String sizeDesc;

  private int sizeInches;

  private Set<Ingredient> ingredients = new HashSet<>();
  private Double price;

  @Override
  public int compareTo(Pizza o) {
    return price.compareTo(o.getPrice());
  }
}
