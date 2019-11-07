package edu.neu.khoury.cs5500.dijkstraspizza.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * An address object with lombok generated boilerplate code (constructor, equals, hashCode, toString,
 * getters, setters).
 */

@Data
public class Address {

  // NonNull tags are for lombok -> creates a constructor that includes them
  @NonNull
  private String streetAddr;
  @NonNull
  private String city;
  @NonNull
  private String state;
  @NonNull
  private String zip;
}
