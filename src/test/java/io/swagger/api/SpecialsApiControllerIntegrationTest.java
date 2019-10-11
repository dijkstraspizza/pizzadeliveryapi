package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Ingredient;
import io.swagger.model.Special;

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
public class SpecialsApiControllerIntegrationTest {

    @Autowired
    private SpecialsApi api;

    @Test
    public void deleteSpecialByIdTest() throws Exception {
        Integer specialId = 56;
        ResponseEntity<Void> responseEntity = api.deleteSpecialById(specialId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getSpecialByIdTest() throws Exception {
        Integer specialId = 56;
        ResponseEntity<Special> responseEntity = api.getSpecialById(specialId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void newSpecialTest() throws Exception {
        Special body = new Special();
        ResponseEntity<Ingredient> responseEntity = api.newSpecial(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateSpecialByIdTest() throws Exception {
        Special body = new Special();
        Integer specialId = 56;
        ResponseEntity<Ingredient> responseEntity = api.updateSpecialById(body, specialId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
