package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Pizza;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PizzasApiControllerIntegrationTest {

    @Autowired
    private PizzasApi api;

    @Test
    public void deletePizzaByIdTest() throws Exception {
        Integer pizzaId = 56;
        ResponseEntity<Void> responseEntity = api.deletePizzaById(pizzaId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getPizzaByIdTest() throws Exception {
        Integer pizzaId = 56;
        ResponseEntity<Pizza> responseEntity = api.getPizzaById(pizzaId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void newPizzaTest() throws Exception {
        Pizza body = new Pizza();
        ResponseEntity<Pizza> responseEntity = api.newPizza(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updatePizzaByIdTest() throws Exception {
        Pizza body = new Pizza();
        Integer pizzaId = 56;
        ResponseEntity<Pizza> responseEntity = api.updatePizzaById(body, pizzaId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
