package edu.neu.khoury.cs5500.dijkstraspizza.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A Menu object with lombok generated boilerplate code (constructor, equals, hashCode, toString,
 * getters, setters).
 */
@Document(collection = "menus")
@Data
public class Menu {

  @Id
  private String id;
  private Set<String> pizzaIds = new HashSet<>();
  private Set<String> ingredientIds = new HashSet<>();
}
