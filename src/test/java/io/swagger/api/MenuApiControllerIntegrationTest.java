package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Ingredient;
import io.swagger.model.Menu;
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
public class MenuApiControllerIntegrationTest {

    @Autowired
    private MenuApi api;

    @Test
    public void deleteMenuByIdTest() throws Exception {
        Integer menuId = 56;
        ResponseEntity<Void> responseEntity = api.deleteMenuById(menuId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getAllMenusTest() throws Exception {
        ResponseEntity<List<Menu>> responseEntity = api.getAllMenus();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getAllPizzasByMenuTest() throws Exception {
        Integer menuId = 56;
        ResponseEntity<List<Pizza>> responseEntity = api.getAllPizzasByMenu(menuId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getIngredientsTest() throws Exception {
        Integer menuId = 56;
        String category = "category_example";
        ResponseEntity<List<Ingredient>> responseEntity = api.getIngredients(menuId, category);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getMenuByIdTest() throws Exception {
        Integer menuId = 56;
        ResponseEntity<Menu> responseEntity = api.getMenuById(menuId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void newMenuTest() throws Exception {
        Menu body = new Menu();
        ResponseEntity<Menu> responseEntity = api.newMenu(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateMenuByIdTest() throws Exception {
        Menu body = new Menu();
        Integer menuId = 56;
        ResponseEntity<Menu> responseEntity = api.updateMenuById(body, menuId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
