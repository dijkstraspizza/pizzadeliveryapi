package edu.neu.khoury.cs5500.dijkstraspizza.repository;

import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaStore;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaStoreRepository extends MongoRepository<PizzaStore, String> {

}
