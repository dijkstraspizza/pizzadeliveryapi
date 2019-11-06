package edu.neu.khoury.cs5500.dijkstraspizza.repository;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Intermediate layer for accessing database data via API controllers.
 */
public interface IngredientRepository extends MongoRepository<Ingredient, String> {

  List<Ingredient> findByCategory(String category);
}
