package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaRepository;
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

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PizzaController.class)
@ContextConfiguration(classes =
    {TestContext.class, WebApplicationContext.class, PizzaController.class})
@WebAppConfiguration
public class PizzaControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PizzaRepository pizzaRepository;

	@Autowired
	private ObjectMapper mapper;

	// Set up pizzas and behavior here =============== 
	private final Ingredient mushrooms = new Ingredient("mushroom", "vegetable", true);
	private final Ingredient sausage = new Ingredient("sausage", "meat", true);
	private final Ingredient nonGfCrust = new Ingredient("crust", "crust", false);
	private final Ingredient gFCrust = new Ingredient("glutenFreeCrust", "crust", true);
	private final Ingredient spinach = new Ingredient("spinach", "vegetable", true);
	private final Ingredient ham = new Ingredient("ham", "meat", true);
	private final double price = 11.68;
	Set<Ingredient> ingredientsIds = new HashSet<Ingredient>(Arrays.asList(ham, sausage));
	private final Pizza meat = new Pizza();
	private final Pizza veggie = new Pizza();
  
	@Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
	veggie.setIngredients(new HashSet<Ingredient>(Arrays.asList(spinach, mushrooms)));
  meat.setIngredients(new HashSet<Ingredient>(Arrays.asList(ham, sausage)));
  veggie.setPrice(11.68);
  meat.setPrice(11.68);
  meat.setSizeDesc("large");
  veggie.setSizeDesc("small");
  meat.setSizeInches(12);
  veggie.setSizeInches(12);
  meat.setId("meatId");
  veggie.setId("veggieId");
  
}

private static class Behavior {
    PizzaRepository pizzaRepository;

    public static Behavior set(PizzaRepository pizzaRepository) {
      Behavior behavior = new Behavior();
      behavior.pizzaRepository = pizzaRepository;
      return behavior;
    }

    public Behavior hasNoPizzas() {
      when(pizzaRepository.findAll()).thenReturn(Collections.emptyList());
      // when(pizzaRepository.findByCategory(anyString())).thenReturn(Collections.emptyList());
      when(pizzaRepository.findById(anyString())).thenReturn(Optional.empty());
      when(pizzaRepository.existsById(anyString())).thenReturn(false);
      return this;
    }

    public Behavior returnSame() {
      when(pizzaRepository.save(any()))
          .thenAnswer(invocation -> invocation.getArguments()[0]);
      return this;
    }

    public Behavior returnPizzas(Pizza... pizzas) {
      when(pizzaRepository.findAll()).thenReturn(Arrays.asList(pizzas));
      when(pizzaRepository.findById(any()))
          .thenAnswer(invocationOnMock -> {
            for (Pizza p : pizzas) {
        if (p.getId().equals(invocationOnMock.getArguments()[0])){
            return Optional.of(p);
        }
      }
      return Optional.empty();
    });
        
      when(pizzaRepository.existsById(anyString())).thenAnswer(invocationOnMock -> {
        for (Pizza p : pizzas) {
          if (p.getId().equals((String) invocationOnMock.getArguments()[0])) {
            return true;
          }
        }
        return false;
      });
      return this;
    }
  }

  // End setup Pizzas and behavior here======================
  
  @Test
	public void testGetAllPizzasHasNoPizzas() throws Exception {
    Behavior.set(pizzaRepository).hasNoPizzas();
    mvc.perform(get("/pizzas/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("[]"));
	}
  
  @Test
	public void testGetAllPizzas() throws Exception {
    Behavior.set(pizzaRepository).returnPizzas(veggie, meat);
    List<Pizza> pizzas = Arrays.asList(veggie, meat);
    String pizzasContent = mapper.writeValueAsString(pizzas);
    mvc.perform(get("/pizzas/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(pizzasContent));
	}

	@Test
	public void testGetPizzaByIdNoPizzas() throws Exception {
    Behavior.set(pizzaRepository).hasNoPizzas();
    mvc.perform(get("/pizzas/hamId"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
	}

	@Test
	public void testNewPizza() throws Exception {
    Behavior.set(pizzaRepository).returnSame();
    String pizzaContent = mapper.writeValueAsString(meat);
    mvc.perform(post("/pizzas/")
        .content(pizzaContent)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(pizzaContent));
	}

	@Test
	public void testUpdatePizzaById() throws Exception {

	}

	@Test
	public void testDeletePizzaById() throws Exception {
    Behavior.set(pizzaRepository).returnPizzas(meat, veggie);
    mvc.perform(delete("/pizzas/meatId"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
	}

}
