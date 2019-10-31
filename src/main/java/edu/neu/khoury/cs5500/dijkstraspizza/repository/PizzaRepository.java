package edu.neu.khoury.cs5500.dijkstraspizza.repository;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaRepository extends MongoRepository<Pizza, String> {

}
