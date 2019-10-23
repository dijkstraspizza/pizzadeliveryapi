package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Ingredient;

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
public class IngredientsApiControllerIntegrationTest {

    @Autowired
    private IngredientsApi api;

    @Test
    public void deleteIngredientByIdTest() throws Exception {
        Integer ingredientId = 56;
        ResponseEntity<Void> responseEntity = api.deleteIngredientById(ingredientId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getIngredientByIdTest() throws Exception {
        Integer ingredientId = 56;
        ResponseEntity<Ingredient> responseEntity = api.getIngredientById(ingredientId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void newIngredientTest() throws Exception {
        Ingredient body = new Ingredient();
        ResponseEntity<Ingredient> responseEntity = api.newIngredient(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateIngredientByIdTest() throws Exception {
        Ingredient body = new Ingredient();
        Integer ingredientId = 56;
        ResponseEntity<Ingredient> responseEntity = api.updateIngredientById(body, ingredientId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
