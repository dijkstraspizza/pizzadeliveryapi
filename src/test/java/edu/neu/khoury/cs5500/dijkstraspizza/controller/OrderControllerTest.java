package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

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

  @Autowired
  ObjectMapper mapper;



  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void getOrderById() {
  }

  @Test
  public void newOrder() {
  }

  @Test
  public void updateOrderById() {
  }
}