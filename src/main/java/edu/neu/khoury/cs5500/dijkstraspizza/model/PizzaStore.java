package edu.neu.khoury.cs5500.dijkstraspizza.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stores")
@Data
public class PizzaStore {

  @Id
  private String id;
  // NonNull tags are for lombok -> creates a constructor that includes them
  @NonNull
  private Address address;
  private Menu menu;
}
