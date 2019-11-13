package edu.neu.khoury.cs5500.dijkstraspizza.model;

/**
 * Price represents the price of a pizza at Dijkstra's Pizza.
 */
public class Price {

  private double price;

  /**
   * Constructor for Price
   *
   * @param price the given price as a double
   */
  public Price(double price) {
    this.price = price;
  }

  /**
   * Gets the price value.
   *
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the new price value.
   *
   * @param price the new price
   */
  public void setPrice(double price) {
    this.price = price;
  }
}
