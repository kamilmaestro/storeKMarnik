package com.marnikkamil.store.order.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class NewOrderDto {

  String id;
  String supplierId;
  List<FoodToOrderDto> food;

}