package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaSize;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PizzaSizeRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
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


@RunWith(SpringRunner.class)
@WebMvcTest(PizzaSizeController.class)
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class,
    PizzaSizeController.class})
public class PizzaSizeControllerTest {

  @Autowired
  WebApplicationContext context;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  PizzaSizeRepository repository;

  @Autowired
  ObjectMapper mapper;

  private PizzaSize small;
  private PizzaSize medium;
  private PizzaSize large;
  private PizzaSize huge;

  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    small = PizzaSize.small(8);
    small.setId("smallId");
    medium = PizzaSize.medium(10);
    medium.setId("mediumId");
    large = PizzaSize.large(12);
    large.setId("largeId");
    huge = new PizzaSize(30.0, "huge", 30.0);
  }

  @Test
  public void getAllPizzaSizesNoSizes() throws Exception {
    Behavior.set(repository).hasNoData();
    mockMvc.perform(get("/sizes/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("[]"));
  }

  @Test
  public void getAllPizzaSizesSomeSizes() throws Exception {
    Behavior.set(repository).returnSizes(small, medium, large, huge);
    String content = mapper.writeValueAsString(Arrays.asList(small, medium, large, huge));
    mockMvc.perform(get("/sizes/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(content));
  }

  @Test
  public void getPizzaSizeByIdNoPizzas() throws Exception {
    Behavior.set(repository).hasNoData();
    mockMvc.perform(get("/sizes/anId"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void getPizzaSizeByIdSomePizzas() throws Exception {
    Behavior.set(repository).returnSizes(small, medium, large);
    mockMvc.perform(get("/sizes/" + medium.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(mapper.writeValueAsString(medium)));
  }

  @Test
  public void newPizzaSize() throws Exception {
    Behavior.set(repository).returnSame(huge);
    String requestContent = mapper.writeValueAsString(huge);
    String responseContent = mapper.writeValueAsString(huge);
    mockMvc.perform(post("/sizes/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(requestContent))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(responseContent));
  }

  @Test
  public void deletePizzaSizeByIdNoMatch() throws Exception {
    Behavior.set(repository).hasNoData();
    mockMvc.perform(delete("/sizes/anyId"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void deletePizzaSizeByIdHasMatch() throws Exception {
    Behavior.set(repository).returnSizes(small, medium, large);
    mockMvc.perform(delete("/sizes/" + large.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  public void testPizzaSizeStaticConstructors() {
    assertEquals(8.0, small.getValue(), 0);
    assertEquals(10.0, medium.getValue(), 0);
    assertEquals(12.0, large.getValue(), 0);

    assertEquals("small", small.getDescription());
    assertEquals("medium", medium.getDescription());
    assertEquals("large", large.getDescription());
  }

  private static class Behavior {

    PizzaSizeRepository repository;

    public static Behavior set(PizzaSizeRepository repository) {
      Behavior behavior = new Behavior();
      behavior.repository = repository;
      return behavior;
    }

    public void hasNoData() {
      when(repository.findAll()).thenReturn(Collections.emptyList());
      when(repository.existsById(anyString())).thenReturn(false);
    }

    public void returnSizes(PizzaSize... sizes) {
      when(repository.findAll()).thenReturn(Arrays.asList(sizes));
      when(repository.existsById(anyString())).thenAnswer(invocationOnMock -> {
        for (PizzaSize size : sizes) {
          if (size.getId().equals(invocationOnMock.getArguments()[0])) {
            return true;
          }
        }
        return false;
      });
      when(repository.findById(anyString())).thenAnswer(invocationOnMock -> {
        for (PizzaSize size : sizes) {
          if (size.getId().equals(invocationOnMock.getArguments()[0])) {
            return Optional.of(size);
          }
        }
        return Optional.empty();
      });
    }

    public void returnSame(PizzaSize size) {
      when(repository.save(any())).thenAnswer(invocationOnMock -> {
        size.setId("newId");
        return size;
      });
    }
  }
}