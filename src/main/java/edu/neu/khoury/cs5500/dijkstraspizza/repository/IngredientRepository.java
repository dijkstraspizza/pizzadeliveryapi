package edu.neu.khoury.cs5500.dijkstraspizza.repository;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {

  public List<Ingredient> findByCategory(String category);
}
