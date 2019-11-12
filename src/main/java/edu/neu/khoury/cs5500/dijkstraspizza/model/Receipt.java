package edu.neu.khoury.cs5500.dijkstraspizza.model;

import lombok.Data;

import java.util.Map;

@Data
public class Receipt {

  private Map<String, Double> pizzaPrices;
  private Map<String, Double> discount = Map.of("No discount", 0.0);
  private Double tax;
  private Double total;

}
