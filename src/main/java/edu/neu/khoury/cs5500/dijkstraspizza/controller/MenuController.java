package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Menu;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.MenuRepository;
import io.swagger.annotations.Api;
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

@Api(value = "menus", description = "Provides CRUD operations for menu objects")
@RestController
@RequestMapping("/menus")
public class MenuController {

  @Autowired
  private MenuRepository repository;

  /*===== GET Methods =====*/

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List getAllMenus() {
    return repository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Menu getMenuById(@PathVariable("id") String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with id=" + id + " not found.");
    }
    return repository.findById(id).get();
  }

  /*===== POST Methods=====*/

  // TODO: Prevent POST methods from allowing an ID field
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Menu newMenu(@Valid @RequestBody Menu menu) {
    repository.save(menu);
    return menu;
  }

  /*===== PUT Methods=====*/

  @RequestMapping(value = "/", method = RequestMethod.PUT)
  public void updateMenuById(@Valid @RequestBody Menu menu) {
    String id = menu.getId();
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with id=" + id + " not found.");
    }
    repository.save(menu);
  }

  /*===== DELETE Methods=====*/

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteMenuById(@PathVariable("id") String id) {
    repository.deleteById(id);
  }
}
