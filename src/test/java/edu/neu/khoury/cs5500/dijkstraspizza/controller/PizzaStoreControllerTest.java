package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Address;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Menu;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaStore;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.MenuRepository;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaStoreRepository;

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
@WebMvcTest(PizzaStoreController.class)
@ContextConfiguration(classes =
    {TestContext.class, WebApplicationContext.class, PizzaStoreController.class})
@WebAppConfiguration
public class PizzaStoreControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PizzaStoreRepository pizzaStoreRepository;


	@Autowired
	private ObjectMapper mapper;

		// Set up pizzas and behavior here =============== 
		private Menu vegMenu = new Menu();
		private Menu nonVegMenu = new Menu();
		private Menu glutenFreeMenu = new Menu();
		private Address firstAddr, storeAddr2, storeAddr3;

		private PizzaStore first, store2, store3, store4;
		
		private Menu regular, glutenFree;
		@Before
		public void setUp() throws Exception {
		  MockitoAnnotations.initMocks(this);
		  mvc = MockMvcBuilders.webAppContextSetup(context).build();
	  
		  // Ingredients Setup
		  Ingredient spinach = new Ingredient("Spinach", "Veggie", true);
		  Ingredient mushroom = new Ingredient("Mushrooms", "Veggie", true);
		  Ingredient ham = new Ingredient("Ham", "Meat", true);
		  Ingredient sausage = new Ingredient("Sausage", "Meat", true);
		  Ingredient pepperoni = new Ingredient("Pepperoni", "Meat", true);
		  Ingredient gfDough = new Ingredient("glutenFreeDough", "Crust", true);
		  spinach.setId("spinachId");
		  mushroom.setId("mushroomsId");
		  ham.setId("hamId");
		  sausage.setId("sausageId");
		  pepperoni.setId("pepperoniId");
		  gfDough.setId("glutenFreeDoughId");
	  
		  // Pizza Setup
		  // spinach
		  Pizza spinachPizza = new Pizza();
		  spinachPizza.setId("spinachPizza");
		  spinachPizza.setIngredients(new HashSet<>(Collections.singletonList(spinach)));
		  // mushroom
		  Pizza mushroomPizza = new Pizza();
		  mushroomPizza.setId("mushroomPizza");
		  mushroomPizza.setIngredients(new HashSet<>(Collections.singletonList(mushroom)));
		  // veggie
		  Pizza vegPizza = new Pizza();
		  vegPizza.setId("vegPizza");
		  vegPizza.setIngredients(new HashSet<>(Arrays.asList(spinach, mushroom)));
		  // ham
		  Pizza hamPizza = new Pizza();
		  hamPizza.setId("hamPizza");
		  hamPizza.setIngredients(new HashSet<>(Collections.singletonList(ham)));
		  // sausage
		  Pizza sausagePizza = new Pizza();
		  sausagePizza.setId("sausagePizza");
		  sausagePizza.setIngredients(new HashSet<>(Collections.singletonList(sausage)));
		  // meat
		  Pizza meatPizza = new Pizza();
		  meatPizza.setId("meatPizza");
		  meatPizza.setIngredients(new HashSet<>(Arrays.asList(ham, sausage)));
		  // gf pizza
		  Pizza gfPizza = new Pizza();
		  gfPizza.setId("glutenFreePizzaId");
		  gfPizza.setIngredients(new HashSet<>(Arrays.asList(gfDough, pepperoni)));
	  
		  vegMenu.setId("vegMenuId");
		  vegMenu.setIngredients(new HashSet<>(Arrays.asList(spinach, mushroom)));
		  vegMenu.setPizzas(new HashSet<>(Arrays.asList(spinachPizza, mushroomPizza, vegPizza)));
	  
		  nonVegMenu.setId("nonVegMenuId");
		  nonVegMenu.setIngredients(new HashSet<>(Arrays.asList(ham, sausage)));
		  nonVegMenu.setPizzas(new HashSet<>(Arrays.asList(hamPizza, sausagePizza, meatPizza)));
	  
		  glutenFreeMenu.setId("glutenFreeMenuId");
		  glutenFreeMenu.setIngredients(new HashSet<>(Arrays.asList(gfDough, pepperoni)));
		  glutenFreeMenu.setPizzas(new HashSet<>(Collections.singletonList(gfPizza)));

		  firstAddr = new Address("6010 15th Ave NW", "Seattle", "WA", "98107");
		  storeAddr2 = new Address("601 N 34th St", "Seattle", "WA", "98103");
		  storeAddr3 = new Address("1302 6th Ave", "Seattle", "WA", "98101");

		  	first = new PizzaStore(firstAddr);
				first.setMenu(regular);
				first.setId("firstId");

			store2 = new PizzaStore(storeAddr2);
			store2.setMenu(glutenFree);

			store3 = new PizzaStore(storeAddr3);
			store3.setMenu(glutenFree);
			store4 = new PizzaStore(storeAddr3);
			store4.setMenu(glutenFree);


		}
	
	private static class Behavior {
		PizzaStoreRepository pizzaStoreRepository;
	
		public static Behavior set(PizzaStoreRepository pizzaRepository) {
		  Behavior behavior = new Behavior();
		  behavior.pizzaStoreRepository = pizzaRepository;
		  return behavior;
		}
	
		public Behavior hasNoPizzasStores() {
		  when(pizzaStoreRepository.findAll()).thenReturn(Collections.emptyList());
		  // when(pizzaRepository.findByCategory(anyString())).thenReturn(Collections.emptyList());
		  when(pizzaStoreRepository.findById(anyString())).thenReturn(Optional.empty());
		  when(pizzaStoreRepository.existsById(anyString())).thenReturn(false);
		  return this;
		}
	
		public Behavior returnSame() {
		  when(pizzaStoreRepository.save(any()))
			  .thenAnswer(invocation -> invocation.getArguments()[0]);
		  return this;
		}
	
		public Behavior returnPizzasStores(PizzaStore... pizzaStore) {
		  when(pizzaStoreRepository.findAll()).thenReturn(Arrays.asList(pizzaStore));
		  when(pizzaStoreRepository.findById(any()))
			  .thenAnswer(invocationOnMock -> {
				for (PizzaStore p : pizzaStore) {
			if (p.getId().equals(invocationOnMock.getArguments()[0])){
				return Optional.of(p);
			}
		  }
		  return Optional.empty();
		});
			
		  when(pizzaStoreRepository.existsById(anyString())).thenAnswer(invocationOnMock -> {
			for (PizzaStore p : pizzaStore) {
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
	public void testGetAllStoresNoStores() throws Exception {
		Behavior.set(pizzaStoreRepository).hasNoPizzasStores();
    	mvc.perform(get("/stores/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("[]"));

	}

	@Test
	public void getAllStoresSomeStores() throws Exception {
		Behavior.set(pizzaStoreRepository).returnPizzasStores(first, store2);
		String stores = mapper.writeValueAsString(Arrays.asList(first, store2));
		mvc.perform(get("/stores/"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json(stores));
	}

	@Test
	public void testGetStoreByIdSomeStoresMatch() throws Exception {
		Behavior.set(pizzaStoreRepository).returnPizzasStores(first, store2);
		String stores = mapper.writeValueAsString(first);
    	mvc.perform(get("/stores/firstId"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(stores));
  }


	@Test
	public void testNewStore() throws Exception {
		
		// Behavior.set(pizzaStoreRepository).returnSame();
		// String store = mapper.writeValueAsString(store2);
		// mvc.perform(post("/stores/")
		// .content(store)
		// .contentType(MediaType.APPLICATION_JSON_UTF8))
        // .andExpect(status().isCreated())
        // // .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        // .andExpect(content().json(store));
	}

	@Test
	public void testUpdateStoreById() throws Exception {
		// Behavior.set(pizzaStoreRepository).returnPizzasStores(first, store2);
		// Address addressNew = new Address("test", "test", "test", "test");
		// String store = mapper.writeValueAsString(first);
		// first.setAddress(addressNew);
		// mvc.perform(put("/stores/")
		// .content(store)
		// .contentType(MediaType.APPLICATION_JSON_UTF8))
        // .andExpect(status().isOk())
        // .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        // .andExpect(content().json(store));
	}

	@Test
	public void testDeleteStoreById() throws Exception {

	}

}
