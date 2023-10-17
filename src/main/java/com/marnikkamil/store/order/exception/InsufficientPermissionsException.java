package com.marnikkamil.store.order.exception;

public class InsufficientPermissionsException extends RuntimeException {

  public InsufficientPermissionsException(String userId) {
    super("User with an ID: " + userId + " does not have permissions to perform an action");
  }

}