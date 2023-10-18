package com.marnikkamil.store.supplier.domain;

import com.marnikkamil.store.common.Money;
import com.marnikkamil.store.supplier.dto.FoodDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Document("food")
@Getter(AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE)
class Food {

  @Setter
  @MongoId
  ObjectId id;
  String name;
  ObjectId supplierId;
  Money price;

  FoodDto dto() {
    return FoodDto.builder()
        .id(this.id.toString())
        .name(this.name)
        .supplierId(this.supplierId.toString())
        .price(this.price.getValueAsDouble())
        .build();
  }

}
