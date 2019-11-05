package edu.neu.khoury.cs5500.dijkstraspizza.repository;

import edu.neu.khoury.cs5500.dijkstraspizza.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Intermediate layer for accessing database data via API controllers.
 */
public interface AddressRepository extends MongoRepository<Address, String> {

}
