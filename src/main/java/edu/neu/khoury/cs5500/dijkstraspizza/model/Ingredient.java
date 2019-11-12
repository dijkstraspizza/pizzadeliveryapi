package edu.neu.khoury.cs5500.dijkstraspizza.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A Ingredient object with lombok generated boilerplate code (constructor, equals, hashCode,
 * toString, getters, setters).
 */
@Document(collection = "ingredients")
@Data
public class Ingredient {

  @Id
  private String id;
  // NonNull tags are for lombok -> creates a constructor that includes them
  @NonNull
  private String name;
  @NonNull
  private String category;
  @NonNull
  private Boolean isGlutenFree;
}
