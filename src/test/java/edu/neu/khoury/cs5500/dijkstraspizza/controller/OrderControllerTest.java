package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Address;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Order;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PriceCalculator;
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

    cheesePizza = new Pizza();
    cheesePizza.setPrice(10.0);

    pepperoniPizza = new Pizza();
    pepperoniPizza.setPrice(12.0);

    hugePizza = new Pizza();
    hugePizza.setPrice(30.0);

    bigOrder = new Order(store, customer);
    bigOrder.setPizzas(Arrays.asList(pepperoniPizza, cheesePizza, hugePizza));
    bigOrder.setId("big-id");

    smallOrder = new Order(store, customer);
    smallOrder.setPizzas(Arrays.asList(cheesePizza, pepperoniPizza));
    smallOrder.setId("small-id");

    generic = new PriceCalculator();
    generic.setId("generic-price");

    halfOffAll = new PriceCalculator(.5);
    halfOffAll.setId("half-off");

    bogo = new PriceCalculator(2, 1, 1.0);
    bogo.setId("bogo");

    when(priceCalculatorController.getOrderPrice(eq(Optional.empty()), any(Order.class)))
        .thenAnswer(invocationOnMock ->
            generic.calculate(((Order) invocationOnMock.getArguments()[1]).getPizzas()));
    when(priceCalculatorController.getOrderPrice(eq(Optional.of("half-off")), any(Order.class)))
        .thenAnswer(invocationOnMock ->
            halfOffAll.calculate(((Order) invocationOnMock.getArguments()[1]).getPizzas()));
    when(priceCalculatorController.getOrderPrice(eq(Optional.of("bogo")), any(Order.class)))
        .thenAnswer(invocationOnMock ->
            bogo.calculate(((Order) invocationOnMock.getArguments()[1]).getPizzas()));
  }

  private static class Behavior {
    OrderRepository repository;

    public static Behavior set(OrderRepository orderRepository) {
      Behavior behavior = new Behavior();
      behavior.repository = orderRepository;
      return behavior;
    }

    public void hasNoData() {
      when(repository.existsById(anyString())).thenReturn(false);
    }

    public void returnOrders(Order... orders) {
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
  public void newOrderHalfOff() throws Exception {
    bigOrder.setSpecialId(halfOffAll.getId());

    Order newOrder = new Order(store, customer);
    newOrder.setPizzas(bigOrder.getPizzas());
    newOrder.setId(bigOrder.getId());
    newOrder.setSpecialId(bigOrder.getSpecialId());
    newOrder.setPrice(52 * .5);

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
  public void newOrderNoDiscount() throws Exception {
    Order newOrder = new Order(store, customer);
    newOrder.setPizzas(bigOrder.getPizzas());
    newOrder.setId(bigOrder.getId());
    newOrder.setSpecialId(bigOrder.getSpecialId());
    newOrder.setPrice(52);

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
  public void updateOrderByIdNoMatch() throws Exception {
    Behavior.set(repository).hasNoData();
    String content = mapper.writeValueAsString(bigOrder);
    mockMvc.perform(put("/orders/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(content))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void updateOrderByIdHasMatch() throws Exception {
    Behavior.set(repository).returnOrders(bigOrder);
    bigOrder.setPizzas(smallOrder.getPizzas());
    String requestContent = mapper.writeValueAsString(bigOrder);
    mockMvc.perform(put("/orders/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(requestContent))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
  }
}