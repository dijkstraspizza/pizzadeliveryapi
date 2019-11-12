package edu.neu.khoury.cs5500.dijkstraspizza.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A PizzaStore object with lombok generated boilerplate code (constructor, equals, hashCode,
 * toString, getters, setters).
 */
@Document(collection = "stores")
@Getter
@Setter
public class PizzaStore {

  @Id
  private String id;
  private Address address;
  private Menu menu;

  public PizzaStore() {
  }

  public PizzaStore(Address address) {
    this.address = address;
  }

  public PizzaStore(Address address, Menu menu) {
    this.address = address;
    this.menu = menu;
  }


}
