package edu.neu.khoury.cs5500.dijkstraspizza.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * A Pizza object with lombok generated boilerplate code (constructor, equals, hashCode, toString,
 * getters, setters).
 */
@Document(collection = "pizzas")
@Data
public class Pizza implements Comparable<Pizza>{

  public static enum PizzaSize {

    SMALL(8),
    MEDIUM(10),
    LARGE(12);

    int value;

    PizzaSize(int i) {
      this.value = i;
    }

    public int getValue() {
      return this.value;
    }


  }

  @Id
  private String id;
  private String name;
  @Enumerated(EnumType.STRING)
  private PizzaSize sizeDesc;

  private int sizeInches;

  private Set<Ingredient> ingredients = new HashSet<>();
  private Double price;

  @Override
  public int compareTo(Pizza o) {
    return price.compareTo(o.getPrice());
  }
}
