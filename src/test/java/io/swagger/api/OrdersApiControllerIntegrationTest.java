package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Order;

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
public class OrdersApiControllerIntegrationTest {

    @Autowired
    private OrdersApi api;

    @Test
    public void addOrderTest() throws Exception {
        Order body = new Order();
        ResponseEntity<Order> responseEntity = api.addOrder(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getOrderByIdTest() throws Exception {
        Integer orderId = 56;
        ResponseEntity<Order> responseEntity = api.getOrderById(orderId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateOrderByIdTest() throws Exception {
        Order body = new Order();
        Integer orderId = 56;
        ResponseEntity<Order> responseEntity = api.updateOrderById(body, orderId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
