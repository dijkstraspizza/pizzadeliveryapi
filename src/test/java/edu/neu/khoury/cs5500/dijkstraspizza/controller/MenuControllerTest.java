package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Menu;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaSize;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.MenuRepository;
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
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(MenuController.class)
@ContextConfiguration(classes =
    {TestContext.class, WebApplicationContext.class, MenuController.class})
@WebAppConfiguration
public class MenuControllerTest {

  @Autowired
  WebApplicationContext context;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  MenuRepository repository;

  @Autowired
  ObjectMapper mapper;

  private Menu vegMenu = new Menu();
  private Menu nonVegMenu = new Menu();
  private Menu glutenFreeMenu = new Menu();

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

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
    Pizza spinachPizza = new Pizza(PizzaSize.small(8));
    spinachPizza.setId("spinachPizza");
    spinachPizza.setIngredients(Collections.singletonList(spinach));
    // mushroom
    Pizza mushroomPizza = new Pizza(PizzaSize.small(8));
    mushroomPizza.setId("mushroomPizza");
    mushroomPizza.setIngredients(Collections.singletonList(mushroom));
    // veggie
    Pizza vegPizza = new Pizza(PizzaSize.small(8));
    vegPizza.setId("vegPizza");
    vegPizza.setIngredients(Arrays.asList(spinach, mushroom));
    // ham
    Pizza hamPizza = new Pizza(PizzaSize.small(8));
    hamPizza.setId("hamPizza");
    hamPizza.setIngredients(Collections.singletonList(ham));
    // sausage
    Pizza sausagePizza = new Pizza(PizzaSize.small(8));
    sausagePizza.setId("sausagePizza");
    sausagePizza.setIngredients(Collections.singletonList(sausage));
    // meat
    Pizza meatPizza = new Pizza(PizzaSize.small(8));
    meatPizza.setId("meatPizza");
    meatPizza.setIngredients(Arrays.asList(ham, sausage));
    // gf pizza
    Pizza gfPizza = new Pizza(PizzaSize.small(8));
    gfPizza.setId("glutenFreePizzaId");
    gfPizza.setIngredients(Arrays.asList(gfDough, pepperoni));

    vegMenu.setId("vegMenuId");
    vegMenu.setIngredients(new HashSet<>(Arrays.asList(spinach, mushroom)));
    vegMenu.setPizzas(new HashSet<>(Arrays.asList(spinachPizza, mushroomPizza, vegPizza)));

    nonVegMenu.setId("nonVegMenuId");
    nonVegMenu.setIngredients(new HashSet<>(Arrays.asList(ham, sausage)));
    nonVegMenu.setPizzas(new HashSet<>(Arrays.asList(hamPizza, sausagePizza, meatPizza)));

    glutenFreeMenu.setId("glutenFreeMenuId");
    glutenFreeMenu.setIngredients(new HashSet<>(Arrays.asList(gfDough, pepperoni)));
    glutenFreeMenu.setPizzas(new HashSet<>(Collections.singletonList(gfPizza)));
  }

  private static class Behavior {
    MenuRepository repository;

    public static Behavior set(MenuRepository repository) {
      Behavior behavior = new Behavior();
      behavior.repository = repository;
      return behavior;
    }

    public void hasNoMenu() {
      when(repository.findAll()).thenReturn(Collections.emptyList());
      when(repository.existsById(anyString())).thenReturn(false);
    }

    public void returnSame() {
      when(repository.save(any())).thenAnswer(mockInvocation -> mockInvocation.getArguments()[0]);
    }

    public void returnMenus(Menu... menus) {
      when(repository.findAll()).thenReturn(Arrays.asList(menus));
      when(repository.existsById(anyString())).thenAnswer(invocationOnMock -> {
        for (Menu menu : menus) {
          if (menu.getId().equals(invocationOnMock.getArguments()[0])) {
            return true;
          }
        }
        return false;
      });
      when(repository.findById(anyString())).thenAnswer(invocationOnMock -> Arrays.stream(menus)
          .filter(menu -> menu.getId().equals(invocationOnMock.getArguments()[0]))
          .collect(Collectors.collectingAndThen(Collectors.toList(),
              list -> Optional.of(list.get(0)))));
    }
  }

  @Test
  public void getAllMenusNoMenu() throws Exception {
    Behavior.set(repository).hasNoMenu();
    mockMvc.perform(get("/menus/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("[]"));
  }

  @Test
  public void getAllMenusSomeMenus() throws Exception {
    Behavior.set(repository).returnMenus(vegMenu, nonVegMenu);
    String menus = mapper.writeValueAsString(Arrays.asList(vegMenu, nonVegMenu));
    mockMvc.perform(get("/menus/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(menus));
  }

  @Test
  public void getMenuByIdNoMenus() throws Exception {
    Behavior.set(repository).hasNoMenu();
    mockMvc.perform(get("/menus/anId"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void getMenuByIdSomeMenusNoMatch() throws Exception {
    Behavior.set(repository).returnMenus(nonVegMenu, glutenFreeMenu);
    mockMvc.perform(get("/menus/vegMenuId"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void getMenuByIdSomeMenusHasMatch() throws Exception {
    Behavior.set(repository).returnMenus(nonVegMenu, glutenFreeMenu);
    String content = mapper.writeValueAsString(nonVegMenu);
    mockMvc.perform(get("/menus/nonVegMenuId"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(content));
  }

  @Test
  public void newMenu() throws Exception {
    Behavior.set(repository).returnSame();
    String content = mapper.writeValueAsString(glutenFreeMenu);
    mockMvc.perform(post("/menus/")
        .content(content)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(content));
  }

  @Test
  public void updateMenuByIdNoMenus() throws Exception {
    Behavior.set(repository).hasNoMenu();
    String content = mapper.writeValueAsString(vegMenu);
    mockMvc.perform(put("/menus/")
        .content(content)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void updateMenuByIdSomeMenusNoMatch() throws Exception {
    Behavior.set(repository).returnMenus(nonVegMenu);
    String content = mapper.writeValueAsString(vegMenu);
    mockMvc.perform(put("/menus/")
        .content(content)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void updateMenuByIdSomeMenusHasMatch() throws Exception {
    Behavior.set(repository).returnMenus(nonVegMenu, vegMenu);
    Ingredient cheese = new Ingredient("Mozzarella", "Cheese", true);
    cheese.setId("mozzarellaId");
    Pizza cheesePizza = new Pizza(PizzaSize.small(8));
    cheesePizza.setId("cheesePizzaId");
    cheesePizza.setIngredients(Collections.singletonList(cheese));

    vegMenu.getPizzas().add(cheesePizza);
    String content = mapper.writeValueAsString(vegMenu);
    mockMvc.perform(put("/menus/")
        .content(content)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void deleteMenuByIdNoMenus() throws Exception {
    Behavior.set(repository).hasNoMenu();
    mockMvc.perform(delete("/menus/nonVegeMenuId"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void deleteMenuByIdSomeMenusNoMatch() throws Exception {
    Behavior.set(repository).returnMenus(vegMenu);
    mockMvc.perform(delete("/menus/nonVegMenuId"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void deleteMenuByIdSomeMenusHasMatch() throws Exception {
    Behavior.set(repository).returnMenus(vegMenu, nonVegMenu);
    mockMvc.perform(delete("/menus/nonVegMenuId"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
  }
}