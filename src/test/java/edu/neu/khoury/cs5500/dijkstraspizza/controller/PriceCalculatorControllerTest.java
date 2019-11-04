package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.khoury.cs5500.dijkstraspizza.model.price.GenericPriceCalculator;
import edu.neu.khoury.cs5500.dijkstraspizza.model.price.IPriceCalculator;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PriceCalculatorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

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

  @Autowired
  ObjectMapper mapper;

  private IPriceCalculator generic;
  private IPriceCalculator halfOffAll;
  private IPriceCalculator bogo;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    generic = new GenericPriceCalculator();
    generic.set
  }

  @Test
  public void getAllPriceCalculators() {
  }

  @Test
  public void getPriceCalculatorByIdHttp() {
  }

  @Test
  public void newPriceCalculator() {
  }

  @Test
  public void deletePriceCalculatorById() {
  }

  @Test
  public void getPriceCalculatorById() {
  }
}