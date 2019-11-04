package edu.neu.khoury.cs5500.dijkstraspizza.controller;

import edu.neu.khoury.cs5500.dijkstraspizza.model.price.IPriceCalculator;
import edu.neu.khoury.cs5500.dijkstraspizza.repository.PriceCalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PriceCalculatorController {

  @Autowired
  private PriceCalculatorRepository repository;

  public IPriceCalculator getPriceCalculatorById(String id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Price Calculator with id=" + id + " not found."
      );
    }
    return repository.findById(id).get();
  }
}
