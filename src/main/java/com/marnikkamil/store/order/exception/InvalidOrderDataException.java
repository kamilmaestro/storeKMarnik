package com.marnikkamil.store.order.exception;

public class InvalidOrderDataException extends RuntimeException {

  public InvalidOrderDataException() {
    super("Can not create order with invalid data");
  }

}