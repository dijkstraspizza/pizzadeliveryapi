<<<<<<< HEAD
package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	// Set up pizzas here =============== 
	private final Ingredient mushrooms = new Ingredient("mushroom", "vegetable", true);
  private final Ingredient sausage = new Ingredient("sausage", "meat", true);
  private final Ingredient nonGfCrust = new Ingredient("crust", "crust", false);
  private final Ingredient gFCrust = new Ingredient("glutenFreeCrust", "crust", true);
  private final Ingredient spinach = new Ingredient("spinach", "vegetable", true);
  private final Ingredient ham = new Ingredient("ham", "meat", true);

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
    mushrooms.setId("mushroomId");
    sausage.setId("sausageId");
    nonGfCrust.setId("nonGfCrustId");
    gFCrust.setId("gFCrustId");
    spinach.setId("spinachId");
    ham.setId("hamId");
  }

  // Setup Pizzas here======================
	@Test
	public void testGetAllPizzas() throws Exception {

	}

	@Test
	public void testGetPizzaById() throws Exception {

	}

	@Test
	public void testNewPizza() throws Exception {

	}

	@Test
	public void testUpdatePizzaById() throws Exception {

	}

	@Test
	public void testDeletePizzaById() throws Exception {

	}

}
=======
// package edu.neu.khoury.cs5500.dijkstraspizza.controller;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
// import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaRepository;
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.TestContext;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;

// import java.util.*;
// import java.util.stream.Collectors;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @RunWith(SpringJUnit4ClassRunner.class)
// @WebMvcTest(PizzaController.class)
// @ContextConfiguration(classes =
//     {TestContext.class, WebApplicationContext.class, PizzaController.class})
// @WebAppConfiguration
// public class PizzaControllerTest {

// 	@Autowired
// 	private WebApplicationContext context;

// 	@Autowired
// 	private MockMvc mvc;

// 	@MockBean
// 	private PizzaRepository pizzaRepository;

// 	@Autowired
// 	private ObjectMapper mapper;

// 	// Set up pizzas here =============== 
// 	private final Ingredient mushrooms = new Ingredient("mushroom", "vegetable", true);
//   private final Ingredient sausage = new Ingredient("sausage", "meat", true);
//   private final Ingredient nonGfCrust = new Ingredient("crust", "crust", false);
//   private final Ingredient gFCrust = new Ingredient("glutenFreeCrust", "crust", true);
//   private final Ingredient spinach = new Ingredient("spinach", "vegetable", true);
//   private final Ingredient ham = new Ingredient("ham", "meat", true);

//   @Before
//   public void setup() {
//     MockitoAnnotations.initMocks(this);
//     mvc = MockMvcBuilders.webAppContextSetup(context).build();
//     mushrooms.setId("mushroomId");
//     sausage.setId("sausageId");
//     nonGfCrust.setId("nonGfCrustId");
//     gFCrust.setId("gFCrustId");
//     spinach.setId("spinachId");
//     ham.setId("hamId");
//   }

//   // Setup Pizzas here======================
// 	@Test
// 	public void testGetAllPizzas() throws Exception {

// 	}

// 	@Test
// 	public void testGetPizzaById() throws Exception {

// 	}

// 	@Test
// 	public void testNewPizza() throws Exception {

// 	}

// 	@Test
// 	public void testUpdatePizzaById() throws Exception {

// 	}

// 	@Test
// 	public void testDeletePizzaById() throws Exception {

// 	}

// }
>>>>>>> 01b4e6074bf68f06d25ca239ac30dc0edb535086
