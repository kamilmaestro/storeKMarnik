package com.marnikkamil.store.supplier.exception;

public class FoodNotFoundException extends RuntimeException {

  public FoodNotFoundException(String message) {
    super(message);
  }

}