package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.controller.validator.OrderValidator;
import edu.neu.khoury.cs5500.dijkstraspizza.controller.validator.Validator;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PriceCalculator;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Receipt;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.OrderRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Api(value = "orders", tags = {"order"})
@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired
  private OrderRepository repository;

  @Autowired
  private PriceCalculatorController priceCalculatorController;

  @Autowired
  private Validator<Order> validator = new OrderValidator();


  /*===== GET Methods =====*/

  @ApiOperation(
      value = "Gets the receipt of a specific order by ID",
      response = Receipt.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/receipt/{id}", method = RequestMethod.GET)
  public Receipt getReceiptById(
      @ApiParam(value = "ID of the order to create a receipt for", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Order with id=" + " not found."
      );
    }
    Order order = repository.findById(id).get();
    return getReceipt(order);
  }

  @ApiOperation(
      value = "Gets a specific order by ID",
      response = Order.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Order getOrderById(
      @ApiParam(value = "ID of the order to return", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Order with id=" + " not found."
      );
    }
    return repository.findById(id).get();
  }

  /*===== POST Methods =====*/

  @ApiOperation(
      value = "Creates a new order in the database",
      notes = "ID is assigned by the database and returned to the caller for further reference. Do not include ID in request.",
      response = Order.class,
      consumes = "application/json",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Order newOrder(
      @ApiParam(value = "JSON Order object without an id field", required = true)
      @Valid @RequestBody Order order) {

    if (order.getId() != null) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "ID field must be null."
      );
    }

    if (!validator.validate(order)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid order. All ingredients and sizes must be " +
          "entities in the database and order must contain valid credit card information."
      );
    }

    Double price = priceCalculatorController.getOrderPrice(
        Optional.ofNullable(order.getSpecialId()), order);
    order.setPrice(price);
    repository.save(order);
    return order;
  }

  /*===== PUT Methods =====*/

  @ApiOperation(
      value = "Updates an existing Order based on the ID in the provided object",
      notes = "ID must match an existing order",
      consumes = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.PUT)
  public void updateOrderById(
      @ApiParam(value = "JSON Order object with an existing order ID and updated fields as needed", required = true)
      @Valid @RequestBody Order order) {
    String id = order.getId();
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Order with id=" + id + " not found.");
    }
    if (!validator.validate(order)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid order. All ingredients and sizes must be " +
          "entities in the database and order must contain valid credit card information."
      );
    }
    Double price = priceCalculatorController.getOrderPrice(
        Optional.ofNullable(order.getSpecialId()), order);
    order.setPrice(price);
    repository.save(order);
  }

  /*===== Non-HTTP Methods =====*/
  private Receipt getReceipt(Order order) {
    Receipt receipt = new Receipt();
    populateReceipt(receipt, order);
    return receipt;
  }

  private void populateReceipt(Receipt receipt, Order order) {
    populatePizzaPrices(receipt, order);
    PriceCalculator priceCalculator = populateDiscount(receipt, order);
    populateTax(receipt, order, priceCalculator);
    populateTotal(receipt, order, priceCalculator);
  }

  private void populateTotal(Receipt receipt, Order order, PriceCalculator priceCalculator) {
    receipt.setTotal(priceCalculator.calculatePrice(order.getPizzas()));
  }

  private void populateTax(Receipt receipt, Order order, PriceCalculator priceCalculator) {
    receipt.setTax(
        priceCalculator.calculateTax(
            priceCalculator.calculateNoTaxPrice(order.getPizzas())));
  }

  private PriceCalculator populateDiscount(Receipt receipt, Order order) {
    if (order.getSpecialId() != null) {
      PriceCalculator priceCalculator = priceCalculatorController
          .getPriceCalculatorById(order.getSpecialId());
      Map<String, Double> discount = Map.of(priceCalculator.getName(),
          priceCalculator.calculateDiscount(order));
      receipt.setDiscount(discount);
      return priceCalculator;
    }
    return new PriceCalculator();
  }

  private void populatePizzaPrices(Receipt receipt, Order order) {
    Map<String, Double> pizzaPrices = new HashMap<>();
    for (Pizza pizza : order.getPizzas()) {
      pizzaPrices.put(pizza.getName(), PriceCalculator.calculatePizzaPrice(pizza.getSizeDesc(),
          pizza.getIngredients().size()));
    }
    receipt.setPizzaPrices(pizzaPrices);
  }
}
