package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

/**
 * An interface for validating entities being added/updated in the database.
 */
public interface Validator<E> {


  /**
   * Validates the given entity.
   *
   * @param entity an object to validate
   * @return True if encapsulated entities exist in the database, false if not.
   */
  boolean validate(E entity);
}
