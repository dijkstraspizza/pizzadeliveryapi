package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.khoury.cs5500.dijkstraspizza.controller.validator.Validator;
import edu.neu.khoury.cs5500.dijkstraspizza.model.*;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class, OrderController.class})
public class OrderControllerTest {

  @Autowired
  WebApplicationContext context;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  OrderRepository repository;

  @MockBean
  PriceCalculatorController priceCalculatorController;

  @MockBean
  Validator<Order> validator;

  @Autowired
  ObjectMapper mapper;


  private static PriceCalculator generic;
  private static PriceCalculator halfOffAll;
  private static PriceCalculator bogo;
  private Pizza cheesePizza;
  private Pizza pepperoniPizza;
  private Pizza hugePizza;

  private Address store;
  private Address customer;

  private Order bigOrder;
  private Order smallOrder;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    store = new Address("123", "Seattle", "WA", "98103");
    customer = new Address("abc", "Seattle", "WA", "98117");

    cheesePizza = new Pizza(PizzaSize.small(8));

    pepperoniPizza = new Pizza(PizzaSize.medium(10));

    hugePizza = new Pizza(PizzaSize.large(12));

    bigOrder = new Order(store, customer);
    bigOrder.setPizzas(Arrays.asList(pepperoniPizza, cheesePizza, hugePizza));
    bigOrder.setId("big-id");

    smallOrder = new Order(store, customer);
    smallOrder.setPizzas(Arrays.asList(cheesePizza, pepperoniPizza));
    smallOrder.setId("small-id");

    generic = new PriceCalculator();
    generic.setId("generic-price");

    halfOffAll = new PriceCalculator(.5, "halfOff");
    halfOffAll.setId("half-off");

    bogo = new PriceCalculator(2, 1, 1.0, "bogo");
    bogo.setId("bogo");

    when(priceCalculatorController.getOrderPrice(eq(Optional.empty()), any(Order.class)))
        .thenAnswer(invocationOnMock ->
            generic.calculatePrice(((Order) invocationOnMock.getArguments()[1]).getPizzas()));
    when(priceCalculatorController.getOrderPrice(eq(Optional.of("half-off")), any(Order.class)))
        .thenAnswer(invocationOnMock ->
            halfOffAll.calculatePrice(((Order) invocationOnMock.getArguments()[1]).getPizzas()));
    when(priceCalculatorController.getOrderPrice(eq(Optional.of("bogo")), any(Order.class)))
        .thenAnswer(invocationOnMock ->
            bogo.calculatePrice(((Order) invocationOnMock.getArguments()[1]).getPizzas()));
  }

  private static class Behavior {
    OrderRepository repository;
    Validator<Order> validator;

    public static Behavior set(OrderRepository orderRepository) {
      Behavior behavior = new Behavior();
      behavior.repository = orderRepository;
      return behavior;
    }

    public static Behavior set(OrderRepository orderRepository, Validator validator) {
      Behavior behavior = new Behavior();
      behavior.repository = orderRepository;
      behavior.validator = validator;
      return behavior;
    }

    public Behavior isValid() {
      when(validator.validate(any())).thenReturn(true);
      return this;
    }

    public Behavior isNotValid() {
      when(validator.validate(any())).thenReturn(false);
      return this;
    }

    public Behavior hasNoData() {
      when(repository.existsById(anyString())).thenReturn(false);
      return this;
    }

    public Behavior returnOrders(Order... orders) {
      when(repository.existsById(anyString()))
          .thenAnswer(invocationOnMock -> {
            for (Order order: orders) {
              if (order.getId().equals(invocationOnMock.getArguments()[0])) {
                return true;
              }
            }
            return false;
          });
      when(repository.findById(anyString()))
          .thenAnswer(invocationOnMock -> {
            for (Order order: orders) {
              if (order.getId().equals(invocationOnMock.getArguments()[0])) {
                return Optional.of(order);
              }
            }
            return Optional.empty();
          });
      return this;
    }
  }

  @Test
  public void getOrderByIdNoMatch() throws Exception {
    Behavior.set(repository).hasNoData();
    mockMvc.perform(get("/orders/anId"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void getOrderByIdHasMatch() throws Exception {
    Behavior.set(repository).returnOrders(bigOrder, smallOrder);
    String content = mapper.writeValueAsString(bigOrder);
    mockMvc.perform(get("/orders/" + bigOrder.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(content));
  }

  @Test
  public void newOrderHalfOffValid() throws Exception {
    bigOrder.setSpecialId(halfOffAll.getId());

    Behavior.set(repository, validator).isValid();

    Order newOrder = new Order(store, customer);
    newOrder.setPizzas(bigOrder.getPizzas());
    newOrder.setSpecialId(bigOrder.getSpecialId());
    newOrder.setPrice(30 * .5);

    bigOrder.setId(null);
    String requestContent = mapper.writeValueAsString(bigOrder);
    String responseContent = mapper.writeValueAsString(newOrder);

    mockMvc.perform(post("/orders/")
        .content(requestContent)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(responseContent));
  }

  @Test
  public void newOrderHalfOffNotValid() throws Exception {
    bigOrder.setSpecialId(halfOffAll.getId());

    Behavior.set(repository, validator).isNotValid();

    Order newOrder = new Order(store, customer);
    newOrder.setPizzas(bigOrder.getPizzas());
    newOrder.setId(bigOrder.getId());
    newOrder.setSpecialId(bigOrder.getSpecialId());
    newOrder.setPrice(30 * .5);

    bigOrder.setId(null);
    String requestContent = mapper.writeValueAsString(bigOrder);
    String responseContent = mapper.writeValueAsString(newOrder);

    mockMvc.perform(post("/orders/")
        .content(requestContent)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void newOrderNoDiscount() throws Exception {
    Order newOrder = new Order(store, customer);
    newOrder.setPizzas(bigOrder.getPizzas());
    newOrder.setSpecialId(bigOrder.getSpecialId());
    newOrder.setPrice(30);

    Behavior.set(repository, validator).isValid();

    bigOrder.setId(null);
    String requestContent = mapper.writeValueAsString(bigOrder);
    String responseContent = mapper.writeValueAsString(newOrder);

    mockMvc.perform(post("/orders/")
        .content(requestContent)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(responseContent));
  }

  @Test
  public void newOrderNoDiscountPreExistingId() throws Exception {
    Order newOrder = new Order(store, customer);
    newOrder.setPizzas(bigOrder.getPizzas());
    newOrder.setSpecialId(bigOrder.getSpecialId());
    newOrder.setPrice(30);

    Behavior.set(repository, validator).isValid();
    String requestContent = mapper.writeValueAsString(bigOrder);
    String responseContent = mapper.writeValueAsString(newOrder);

    mockMvc.perform(post("/orders/")
        .content(requestContent)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void updateOrderByIdNoMatch() throws Exception {
    Behavior.set(repository, validator).hasNoData();
    String content = mapper.writeValueAsString(bigOrder);
    mockMvc.perform(put("/orders/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(content))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void updateOrderByIdHasMatchValid() throws Exception {
    Behavior.set(repository, validator).returnOrders(bigOrder).isValid();
    bigOrder.setPizzas(smallOrder.getPizzas());
    String requestContent = mapper.writeValueAsString(bigOrder);
    mockMvc.perform(put("/orders/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(requestContent))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void updateOrderByIdHasMatchNotValid() throws Exception {
    Behavior.set(repository, validator).returnOrders(bigOrder).isNotValid();
    bigOrder.setPizzas(smallOrder.getPizzas());
    String requestContent = mapper.writeValueAsString(bigOrder);
    mockMvc.perform(put("/orders/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(requestContent))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$").doesNotExist());
  }
}