package com.marnikkamil.store.supplier.exception;

public class IncorrectAmountOfFoodException extends RuntimeException {

  public IncorrectAmountOfFoodException(Integer amount) {
    super("Incorrect amount of food: " + amount);
  }

}