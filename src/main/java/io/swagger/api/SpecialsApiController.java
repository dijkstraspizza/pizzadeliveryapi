package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Ingredient;
import io.swagger.model.Special;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-11T13:23:56.479615-07:00[America/Los_Angeles]")
@Controller
public class SpecialsApiController implements SpecialsApi {

    private static final Logger log = LoggerFactory.getLogger(SpecialsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SpecialsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deleteSpecialById(@Min(0)@ApiParam(value = "a special's unique ID",required=true, allowableValues="") @PathVariable("specialId") Integer specialId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Special> getSpecialById(@Min(0)@ApiParam(value = "a special's unique ID",required=true, allowableValues="") @PathVariable("specialId") Integer specialId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Special>(objectMapper.readValue("{\n  \"offeredAt\" : [ null, null ],\n  \"id\" : 1,\n  \"desc\" : \"50% off two large pizzas\"\n}", Special.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Special>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Special>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Ingredient> newSpecial(@ApiParam(value = "A JSON encoded special object" ,required=true )  @Valid @RequestBody Special body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Ingredient>(objectMapper.readValue("{\n  \"name\" : \"Sausage\",\n  \"isGlutenFree\" : true,\n  \"id\" : 1,\n  \"category\" : \"Meat\"\n}", Ingredient.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Ingredient>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Ingredient> updateSpecialById(@ApiParam(value = "A JSON encoded special object" ,required=true )  @Valid @RequestBody Special body,@Min(0)@ApiParam(value = "a special's unique ID",required=true, allowableValues="") @PathVariable("specialId") Integer specialId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Ingredient>(objectMapper.readValue("{\n  \"name\" : \"Sausage\",\n  \"isGlutenFree\" : true,\n  \"id\" : 1,\n  \"category\" : \"Meat\"\n}", Ingredient.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Ingredient>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Ingredient>(HttpStatus.NOT_IMPLEMENTED);
    }

}
