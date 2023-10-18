package com.marnikkamil.store.order.exception;

public class InsufficientPermissionsException extends RuntimeException {

  private InsufficientPermissionsException(String message) {
    super(message);
  }

  public static InsufficientPermissionsException canNotListOrdersCreatedByOthers(String userId) {
    return new InsufficientPermissionsException(
        "User with an ID: " + userId + " does not have permissions to list orders created by other users"
    );
  }

  public static InsufficientPermissionsException canNotDelete(String userId) {
    return new InsufficientPermissionsException(
        "User with an ID: " + userId + " does not have permissions to delete orders"
    );
  }

}