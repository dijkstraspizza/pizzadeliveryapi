package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import edu.neu.khoury.cs5500.dijkstraspizza.model.CreditCardInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class CreditCardValidatorTest {

  private CreditCardInfo validCard;
  private CreditCardInfo invalidNumber;
  private CreditCardInfo invalidDate;
  private CreditCardInfo invalidSecurityCode;

  @Autowired
  CreditCardValidator validator;

  @Before
  public void setUp() throws Exception {
    Calendar nextYear = Calendar.getInstance();
    nextYear.add(Calendar.YEAR, 1);
    validCard = new CreditCardInfo("1234567890123456", "123",
         nextYear.getTime());
    invalidNumber = new CreditCardInfo("1234123456", "123",
        nextYear.getTime());
    Calendar lastYear = Calendar.getInstance();
    lastYear.add(Calendar.YEAR, -1);
    invalidDate = new CreditCardInfo("1234567890123456", "123",
        lastYear.getTime());
    invalidSecurityCode = new CreditCardInfo("1234567890123456", "",
        nextYear.getTime());
  }

  @Configuration
  @Import(CreditCardValidator.class)
  static class Config {
  }

  @Test
  public void validateValid() {
    assertTrue(validator.validate(validCard));
  }

  @Test
  public void validateInvalidNumber() {
    assertFalse(validator.validate(invalidNumber));
  }

  @Test
  public void validateInvalidDate() {
    assertFalse(validator.validate(invalidDate));
  }

  @Test
  public void validateInvalidSecurityCode() {
    assertFalse(validator.validate(invalidSecurityCode));
  }
}