package edu.neu.khoury.cs5500.dijkstraspizza.model;

import java.util.List;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
@Data
public class Order {

  @Id
  private String id;
  // NonNull tags are for lombok -> creates a constructor that includes them
  @NonNull
  private Address storeAddr;
  @NonNull
  private Address custAddr;
  private List<Pizza> pizzas;
  private double price;
}
