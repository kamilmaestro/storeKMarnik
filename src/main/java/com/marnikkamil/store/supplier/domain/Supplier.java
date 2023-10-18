package com.marnikkamil.store.supplier.domain;

import com.marnikkamil.store.supplier.dto.FoodDto;
import com.marnikkamil.store.supplier.dto.SupplierDto;
import com.marnikkamil.store.supplier.dto.SupplierMenuDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Document("supplier")
@Getter(AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE)
class Supplier {

  @Setter
  @MongoId
  ObjectId id;
  String name;
  String phoneNumber;
  String address;
  LocalDateTime createdAt;

  SupplierDto dto() {
    return SupplierDto.builder()
        .id(this.id.toString())
        .name(this.name)
        .phoneNumber(this.phoneNumber)
        .address(this.address)
        .createdAt(this.createdAt)
        .build();
  }

  SupplierMenuDto menuDto(List<Food> supplierFood) {
    final List<FoodDto> menu = supplierFood.stream()
        .map(Food::dto)
        .collect(Collectors.toList());

    return SupplierMenuDto.builder()
        .supplier(dto())
        .menu(menu)
        .build();
  }

}
