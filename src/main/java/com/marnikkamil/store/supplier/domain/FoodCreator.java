package com.marnikkamil.store.supplier.domain;

import com.marnikkamil.store.common.Money;
import com.marnikkamil.store.supplier.dto.AddFoodToMenuDto;
import org.bson.types.ObjectId;

final class FoodCreator {

  Food from(AddFoodToMenuDto addFood) {
    return Food.builder()
        .name(addFood.getName())
        .supplierId(new ObjectId(addFood.getSupplierId()))
        .price(new Money(addFood.getPrice()))
        .build();
  }

}
