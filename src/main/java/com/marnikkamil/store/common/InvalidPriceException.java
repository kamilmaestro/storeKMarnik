package com.marnikkamil.store.common;

public class InvalidPriceException extends RuntimeException {

  public InvalidPriceException(Double price) {
    super("Invalid price: " + price);
  }

}