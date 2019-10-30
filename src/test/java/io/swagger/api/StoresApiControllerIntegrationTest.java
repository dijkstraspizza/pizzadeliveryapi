package io.swagger.api;

import io.swagger.model.PizzaStore;

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
public class StoresApiControllerIntegrationTest {

    @Autowired
    private StoresApi api;

    @Test
    public void deleteStoreByIdTest() throws Exception {
        Integer storeId = 56;
        ResponseEntity<Void> responseEntity = api.deleteStoreById(storeId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getStoreByIdTest() throws Exception {
        Integer storeId = 56;
        ResponseEntity<PizzaStore> responseEntity = api.getStoreById(storeId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getStoresTest() throws Exception {
        ResponseEntity<List<PizzaStore>> responseEntity = api.getStores();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void newStoreTest() throws Exception {
        PizzaStore body = new PizzaStore();
        ResponseEntity<PizzaStore> responseEntity = api.newStore(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateStoreByIdTest() throws Exception {
        PizzaStore body = new PizzaStore();
        Integer storeId = 56;
        ResponseEntity<PizzaStore> responseEntity = api.updateStoreById(body, storeId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
