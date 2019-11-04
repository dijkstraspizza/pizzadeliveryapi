package edu.neu.khoury.cs5500.dijkstraspizza.model.price;


import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;

/**
 * Interface for a class that calculates the price of an Order.
 */
public interface IPriceCalculator {

  /**
   * @return The price of the order.
   */
  Double calculate(Order order);

}
