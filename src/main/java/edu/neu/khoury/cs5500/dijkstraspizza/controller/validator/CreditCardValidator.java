package edu.neu.khoury.cs5500.dijkstraspizza.controller.validator;

import edu.neu.khoury.cs5500.dijkstraspizza.model.CreditCardInfo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class CreditCardValidator implements Validator<CreditCardInfo> {

  private static final Integer MAX_NUMBER_LENGTH = 19;
  private static final Integer MIN_NUMBER_LENGTH = 16;

  @Override
  public boolean validate(CreditCardInfo entity) {
    return entity.getCardNumber().length() <= MAX_NUMBER_LENGTH &&
        entity.getCardNumber().length() >= MIN_NUMBER_LENGTH &&
        entity.getSecurityCode().matches("\\d{3}") &&
        entity.getExpirationDate().after(new Date());
  }
}
