package edu.neu.khoury.cs5500.dijkstraspizza.repository;

import edu.neu.khoury.cs5500.dijkstraspizza.model.price.IPriceCalculator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceCalculatorRepository extends MongoRepository<IPriceCalculator, String> {
}
