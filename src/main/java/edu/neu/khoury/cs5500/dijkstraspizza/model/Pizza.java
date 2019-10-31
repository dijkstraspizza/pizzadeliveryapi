package edu.neu.khoury.cs5500.dijkstraspizza.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pizzas")
@Data
public class Pizza {

  @Id
  private String id;
  private int sizeInches;
  private String sizeDesc;
  private Set<String> ingredientIds = new HashSet<>();
  private double price;
}
