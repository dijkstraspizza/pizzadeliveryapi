package edu.neu.khoury.cs5500.dijkstraspizza.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class CreditCardInfo {
  @NonNull
  private String cardNumber;
  @NonNull
  private String securityCode;
  @NonNull
  private Date expirationDate;
}
