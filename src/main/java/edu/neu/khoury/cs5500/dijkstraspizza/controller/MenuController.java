package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.controller.validator.MenuValidator;
import edu.neu.khoury.cs5500.dijkstraspizza.controller.validator.Validator;
import edu.neu.khoury.cs5500.dijkstraspizza.model.Menu;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.MenuRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Api(value = "menus", tags = {"menu"})
@RestController
@RequestMapping("/menus")
public class MenuController {

  @Autowired
  private MenuRepository repository;

  @Autowired
  private Validator<Menu> validator = new MenuValidator();

  /*===== GET Methods =====*/

  @ApiOperation(
      value = "Get all menus available",
      response = Menu.class,
      responseContainer = "List",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List getAllMenus() {
    return repository.findAll();
  }

  @ApiOperation(
      value = "Get a menu by its ID",
      response = Menu.class,
      responseContainer = "List",
      produces = "application/json"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Menu getMenuById(
      @ApiParam(value = "ID of the menu to get", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with id=" + id + " not found.");
    }
    return repository.findById(id).get();
  }

  /*===== POST Methods=====*/

  @ApiOperation(
      value = "Creates a new menu in the database",
      notes = "ID is assigned by the database and returned to the caller for further reference. Do not include ID in request.",
      response = Menu.class,
      consumes = "application/json",
      produces = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Menu newMenu(
      @ApiParam(value = "JSON menu object without an id field", required = true)
      @Valid @RequestBody Menu menu) {
    if (menu.getId() != null) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "ID must be null"
      );
    }
    if (!validator.validate(menu)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid menu. All pizzas, ingredients, and sizes must be " +
          "entities in the database"
      );
    }
    repository.save(menu);
    return menu;
  }

  /*===== PUT Methods=====*/

  @ApiOperation(
      value = "Updates an existing Menu based on the ID in the provided object",
      notes = "ID must match an existing ingredient",
      consumes = "application/json"
  )
  @RequestMapping(value = "/", method = RequestMethod.PUT)
  public void updateMenuById(
      @ApiParam(value = "JSON menu object with an existing menu ID and updated fields as needed", required = true)
      @Valid @RequestBody Menu menu) {
    String id = menu.getId();
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with id=" + id + " not found.");
    }
    if (!validator.validate(menu)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid menu. All pizzas, ingredients, and sizes must be " +
          "entities in the database"
      );
    }
    repository.save(menu);
  }

  /*===== DELETE Methods=====*/

  @ApiOperation(
      value = "Deletes an ingredient from the database based on its ID"
  )
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteMenuById(
      @ApiParam(value = "ID of the ingredient to delete", required = true)
      @PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with id=" + id + " not found.");
    }
    repository.deleteById(id);
  }
}
