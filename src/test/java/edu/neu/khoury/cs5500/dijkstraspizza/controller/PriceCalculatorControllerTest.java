package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.khoury.cs5500.dijkstraspizza.model.*;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PriceCalculatorRepository;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PriceCalculatorController.class)
@ContextConfiguration(classes =
    {TestContext.class, WebApplicationContext.class, PriceCalculatorController.class})
@WebAppConfiguration
public class PriceCalculatorControllerTest {

  @Autowired
  WebApplicationContext context;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  PriceCalculatorRepository repository;

  @MockBean
  PizzaController pizzaController;

  @Autowired
  ObjectMapper mapper;

  private PriceCalculator generic;
  private PriceCalculator halfOffAll;
  private PriceCalculator bogo;
  private Order order;
  private Pizza cheesePizza;
  private Pizza pepperoniPizza;
  private Pizza hugePizza;
  private String pizzaParams;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    Address store = new Address("123", "Seattle", "WA", "98103");
    Address customer = new Address("abc", "Seattle", "WA", "98117");

    cheesePizza = new Pizza();
    cheesePizza.setPrice(10.0);
    cheesePizza.setId("cheese");

    pepperoniPizza = new Pizza();
    pepperoniPizza.setPrice(12.0);
    pepperoniPizza.setId("pepperoni");

    hugePizza = new Pizza();
    hugePizza.setPrice(30.0);
    hugePizza.setId("huge");

    order = new Order(store, customer);
    order.setPizzas(Arrays.asList(pepperoniPizza, cheesePizza, hugePizza));

    generic = new PriceCalculator();
    generic.setId("generic-price");

    halfOffAll = new PriceCalculator(.5, "halfOff");
    halfOffAll.setId("half-off");

    bogo = new PriceCalculator(2, 1, 1.0, "bogo");
    bogo.setId("bogo");

    for (Pizza pizza: new Pizza[] {cheesePizza, pepperoniPizza, hugePizza}) {
      when(pizzaController.getPizzaById(eq(pizza.getId()))).thenReturn(pizza);
    }

    pizzaParams = "pizzaId[]=" + pepperoniPizza.getId() +
    "&pizzaId[]=" + cheesePizza.getId() + "&pizzaId[]=" + hugePizza.getId();
  }

  private static class Behavior {
    PriceCalculatorRepository repository;

    public static Behavior set(PriceCalculatorRepository repository) {
      Behavior behavior = new Behavior();
      behavior.repository = repository;
      return behavior;
    }

    public void hasNoPriceCalculator() {
      when(repository.findAll()).thenReturn(Collections.emptyList());
      when(repository.existsById(anyString())).thenReturn(false);
    }

    public void returnSame() {
      when(repository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
    }

    public void returnPriceCalculators(PriceCalculator... priceCalculators) {
      when(repository.findAll()).thenReturn(Arrays.asList(priceCalculators));
      when(repository.existsById(anyString())).thenAnswer(invocationOnMock -> {
        for (PriceCalculator priceCalculator: priceCalculators) {
          if (priceCalculator.getId().equals(invocationOnMock.getArguments()[0])) {
            return true;
          }
        }
        return false;
      });
      when(repository.findById(anyString())).thenAnswer(invocationOnMock -> Arrays.stream(priceCalculators)
          .filter(priceCalculator -> priceCalculator.getId().equals(invocationOnMock.getArguments()[0]))
          .collect(Collectors.collectingAndThen(Collectors.toList(),
              list -> Optional.of(list.get(0)))));
    }
  }

  @Test
  public void getAllPriceCalculatorsNoCalculators() throws Exception {
    Behavior.set(repository).hasNoPriceCalculator();
    mockMvc.perform(get("/prices/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("[]"));
  }

  @Test
  public void getAllPriceCalculatorsHasCalculators() throws Exception {
    Behavior.set(repository).returnPriceCalculators(generic, bogo);
    String content = mapper.writeValueAsString(Arrays.asList(generic, bogo));
    mockMvc.perform(get("/prices/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(content));
  }

  @Test
  public void getPriceCalculatorByIdHttpNoCalculators() throws Exception {
    Behavior.set(repository).hasNoPriceCalculator();
    mockMvc.perform(get("/prices/anId/"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void getPriceCalculatorByIdHttpHasCalculatorsNoMatch() throws Exception {
    Behavior.set(repository).returnPriceCalculators(halfOffAll, bogo);
    mockMvc.perform(get("/prices/anId/"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void getPriceCalculatorByIdHttpHasCalculatorsMatch() throws Exception {
    Behavior.set(repository).returnPriceCalculators(halfOffAll, bogo);
    String content = mapper.writeValueAsString(halfOffAll);
    mockMvc.perform(get("/prices/half-off/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(content));
  }

  @Test
  public void newPriceCalculator() throws Exception {
    Behavior.set(repository).returnSame();
    String content = mapper.writeValueAsString(halfOffAll);
    mockMvc.perform(post("/prices/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(content))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(content));
  }

  @Test
  public void deletePriceCalculatorByIdNoCalculators() throws Exception {
    Behavior.set(repository).hasNoPriceCalculator();
    mockMvc.perform(delete("/prices/id"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());

  }

  @Test
  public void deletePriceCalculatorByIdSomeCalculators() throws Exception {
    Behavior.set(repository).returnPriceCalculators(bogo, halfOffAll);
    mockMvc.perform(delete("/prices/bogo"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void getOrderPriceNoSpecial() throws Exception {
    Behavior.set(repository).returnPriceCalculators(bogo, halfOffAll, generic);
    Double orderPrice = order.getPizzas().stream().mapToDouble(Pizza::getPrice).sum();
    mockMvc.perform(get("/prices/price?" + pizzaParams))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(String.format("%s", orderPrice)));
  }

  @Test
  public void getOrderPriceHalfOff() throws Exception {
    Behavior.set(repository).returnPriceCalculators(bogo, halfOffAll, generic);
    Double orderPrice = order.getPizzas().stream().mapToDouble(Pizza::getPrice).sum() * 0.5;
    mockMvc.perform(get("/prices/price?special=" + halfOffAll.getId() +
        "&" + pizzaParams))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(String.format("%s", orderPrice)));
  }

  @Test
  public void getOrderPriceBogo() throws Exception {
    Behavior.set(repository).returnPriceCalculators(bogo, halfOffAll, generic);
    Double orderPrice = 42.0;
    mockMvc.perform(get("/prices/price?special=" + bogo.getId() +
        "&" + pizzaParams))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(String.format("%s", orderPrice)));
  }

  @Test
  public void getOrderPriceBadSpecialId() throws Exception {
    Behavior.set(repository).returnPriceCalculators(bogo, halfOffAll, generic);
    mockMvc.perform(get("/prices/price?special=badId&" + pizzaParams))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

}