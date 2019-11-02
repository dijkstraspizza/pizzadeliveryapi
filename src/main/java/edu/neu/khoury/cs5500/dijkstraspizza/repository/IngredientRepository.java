package edu.neu.khoury.cs5500.dijkstraspizza.repository;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Ingredient;
import java.util.List;

//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.MongoRepository;

//@EnableJpaRepositories
public interface IngredientRepository extends MongoRepository<Ingredient, String> {

  public List<Ingredient> findByCategory(String category);
}
