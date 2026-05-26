package com.optiflow.exceptions.custom;

public class SaleNotFoundException extends RuntimeException {
  public SaleNotFoundException(String message) {
    super(message);
  }
}
