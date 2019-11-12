package edu.neu.khoury.cs5500.dijkstraspizza.model;

/**
 * Price represents the price of a pizza at Dijkstra's Pizza.
 */
public class Price {

  private double price;

  public Price(double price) {
    this.price = price;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
