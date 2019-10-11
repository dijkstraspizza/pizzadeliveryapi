package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Ingredient;
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
public class IngredientsApiController implements IngredientsApi {

    private static final Logger log = LoggerFactory.getLogger(IngredientsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public IngredientsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deleteIngredientById(@Min(0)@ApiParam(value = "an ingredient's unique ID",required=true, allowableValues="") @PathVariable("ingredientId") Integer ingredientId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Ingredient> getIngredientById(@Min(0)@ApiParam(value = "an ingredient's unique ID",required=true, allowableValues="") @PathVariable("ingredientId") Integer ingredientId) {
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

    public ResponseEntity<Ingredient> newIngredient(@ApiParam(value = "A JSON encoded ingredient object" ,required=true )  @Valid @RequestBody Ingredient body) {
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

    public ResponseEntity<Ingredient> updateIngredientById(@ApiParam(value = "A JSON encoded ingredient object" ,required=true )  @Valid @RequestBody Ingredient body,@Min(0)@ApiParam(value = "an ingredient's unique ID",required=true, allowableValues="") @PathVariable("ingredientId") Integer ingredientId) {
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
