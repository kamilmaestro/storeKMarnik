package com.marnikkamil.store.order.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class NewOrderDto {

  String id;

  public static NewOrderDto withNewId() {
    return new NewOrderDto(new ObjectId().toString());
  }

}