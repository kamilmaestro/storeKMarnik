package com.marnikkamil.store.supplier.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class AddFoodToMenuDto {

  String name;
  String supplierId;
  Double price;

}
