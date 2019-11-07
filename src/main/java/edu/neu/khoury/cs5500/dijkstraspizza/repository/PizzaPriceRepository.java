package edu.neu.khoury.cs5500.dijkstraspizza.repository;

import edu.neu.khoury.cs5500.dijkstraspizza.model.PizzaSize;
import org.springframework.data.mongodb.repository.MongoRepository;

interface PizzaSizeRepository extends MongoRepository<PizzaSize, String> {
}
