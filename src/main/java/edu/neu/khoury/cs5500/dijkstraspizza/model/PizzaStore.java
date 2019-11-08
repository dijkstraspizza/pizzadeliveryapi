package edu.neu.khoury.cs5500.dijkstraspizza.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

import lombok.Data;
import lombok.NonNull;

/**
 * A PizzaStore object with lombok generated boilerplate code (constructor, equals, hashCode, toString,
 * getters, setters).
 */
@Document(collection = "stores")

// @Data
@Getter
@Setter
public class PizzaStore {

@Id
  private String id;
  // NonNull tags are for lombok -> creates a constructor that includes them
  // @NonNull
  private Address address;
  private Menu menu;
 
  public PizzaStore(Address address){
    this.address = address;
  }

  public PizzaStore(Address address, Menu menu){
    this.address = address;
    this.menu = menu;
  }


  
}
