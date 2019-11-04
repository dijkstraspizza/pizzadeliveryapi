package edu.neu.khoury.cs5500.dijkstraspizza.model.price;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;

/**
 * Interface for a class that calculates the price of an Order.
 */
public interface IPriceCalculator {

  /**
   * @param order An order.
   * @return The order with the price calculated.
   */
  Order calculate(Order order);

}
