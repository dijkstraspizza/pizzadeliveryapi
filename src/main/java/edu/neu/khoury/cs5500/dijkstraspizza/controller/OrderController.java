package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.price.GenericPriceCalculator;
import edu.neu.khoury.cs5500.dijkstraspizza.model.price.IPriceCalculator;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.OrderRepository;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PriceCalculatorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@Api(value = "orders", tags = {"order-controller"})
@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired
  private OrderRepository repository;

  @Autowired
  private PriceCalculatorRepository priceCalculatorRepository;


  /*===== GET Methods =====*/

  @ApiOperation(
      value = "Gets the price of an order with a special",
      notes = "Special ID is included as a query parameter",
      response = Order.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/price", method = RequestMethod.GET)
  public Order getOrderPriceWithSpecial(
      @ApiParam(value = "id of the special to apply", example = "bogoSpecial")
      @RequestParam("special") String specialId, @Valid @RequestBody Order order) {
    if (!priceCalculatorRepository.existsById(specialId)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Special with id=" + specialId + " not found.");
    }
    IPriceCalculator priceCalculator = priceCalculatorRepository.findById(specialId).get();
    return priceCalculator.calculate(order);
  }

  @ApiOperation(
      value = "Gets the price of an order",
      response = Order.class,
      produces = "application/json"
  )
  @RequestMapping(value = "/price", method = RequestMethod.GET)
  public Order getOrderPrice(
      @ApiParam(value = "JSON Order object without a price.", required = true)
      @Valid @RequestBody Order order) {
    IPriceCalculator priceCalculator = new GenericPriceCalculator();
    return priceCalculator.calculate(order);
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
    repository.save(order);
  }
}
