package edu.neu.khoury.cs5500.dijkstraspizza.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * An Order object with lombok generated boilerplate code (constructor, equals, hashCode, toString,
 * getters, setters).
 */
@Document(collection = "orders")
@Data
public class Order {

  @Id
  private String id;
  // NonNull tags are for lombok -> creates a constructor that includes them
  private @NonNull Address storeAddr;
  private @NonNull Address custAddr;
  private CreditCardInfo cardInfo;
  private List<Pizza> pizzas = new ArrayList<>();
  private double price;
  private String specialId;
}
