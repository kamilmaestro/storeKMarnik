package com.marnikkamil.store.order.exception;

public class IncorrectAmountOfFoodException extends RuntimeException {

  public IncorrectAmountOfFoodException(Integer amount) {
    super("Incorrect amount of food: " + amount);
  }

}