package edu.neu.khoury.cs5500.dijkstraspizza.model;

import java.util.ArrayList;
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
  private String storeAddrId;
  @NonNull
  private String custAddrId;
  private List<String> pizzaIds = new ArrayList<>();
  private double price;
  private String specialId;
}
