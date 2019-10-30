package io.swagger.api;

import io.swagger.model.PizzaStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-11T13:23:56.479615-07:00[America/Los_Angeles]")
@Controller
public class StoresApiController implements StoresApi {

    private static final Logger log = LoggerFactory.getLogger(StoresApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public StoresApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deleteStoreById(@Min(0)@ApiParam(value = "a store's unique ID",required=true, allowableValues="") @PathVariable("storeId") Integer storeId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PizzaStore> getStoreById(@Min(0)@ApiParam(value = "a store's unique ID",required=true, allowableValues="") @PathVariable("storeId") Integer storeId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PizzaStore>(objectMapper.readValue("{\n  \"specials\" : [ {\n    \"offeredAt\" : [ null, null ],\n    \"id\" : 1,\n    \"desc\" : \"50% off two large pizzas\"\n  }, {\n    \"offeredAt\" : [ null, null ],\n    \"id\" : 1,\n    \"desc\" : \"50% off two large pizzas\"\n  } ],\n  \"id\" : 1\n}", PizzaStore.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PizzaStore>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PizzaStore>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<PizzaStore>> getStores() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<PizzaStore>>(objectMapper.readValue("[ {\n  \"specials\" : [ {\n    \"offeredAt\" : [ null, null ],\n    \"id\" : 1,\n    \"desc\" : \"50% off two large pizzas\"\n  }, {\n    \"offeredAt\" : [ null, null ],\n    \"id\" : 1,\n    \"desc\" : \"50% off two large pizzas\"\n  } ],\n  \"id\" : 1\n}, {\n  \"specials\" : [ {\n    \"offeredAt\" : [ null, null ],\n    \"id\" : 1,\n    \"desc\" : \"50% off two large pizzas\"\n  }, {\n    \"offeredAt\" : [ null, null ],\n    \"id\" : 1,\n    \"desc\" : \"50% off two large pizzas\"\n  } ],\n  \"id\" : 1\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<PizzaStore>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<PizzaStore>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PizzaStore> newStore(@ApiParam(value = "A JSON encoded PizzaStore object." ,required=true )  @Valid @RequestBody PizzaStore body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PizzaStore>(objectMapper.readValue("{\n  \"specials\" : [ {\n    \"offeredAt\" : [ null, null ],\n    \"id\" : 1,\n    \"desc\" : \"50% off two large pizzas\"\n  }, {\n    \"offeredAt\" : [ null, null ],\n    \"id\" : 1,\n    \"desc\" : \"50% off two large pizzas\"\n  } ],\n  \"id\" : 1\n}", PizzaStore.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PizzaStore>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PizzaStore>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PizzaStore> updateStoreById(@ApiParam(value = "A pizza store with updated information" ,required=true )  @Valid @RequestBody PizzaStore body,@Min(0)@ApiParam(value = "a store's unique ID",required=true, allowableValues="") @PathVariable("storeId") Integer storeId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PizzaStore>(objectMapper.readValue("{\n  \"specials\" : [ {\n    \"offeredAt\" : [ null, null ],\n    \"id\" : 1,\n    \"desc\" : \"50% off two large pizzas\"\n  }, {\n    \"offeredAt\" : [ null, null ],\n    \"id\" : 1,\n    \"desc\" : \"50% off two large pizzas\"\n  } ],\n  \"id\" : 1\n}", PizzaStore.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PizzaStore>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PizzaStore>(HttpStatus.NOT_IMPLEMENTED);
    }

}
