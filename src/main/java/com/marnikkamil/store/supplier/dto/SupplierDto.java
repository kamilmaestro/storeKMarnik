package com.marnikkamil.store.supplier.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode
public final class SupplierDto {

  String id;
  String name;
  String phoneNumber;
  String address;
  LocalDateTime createdAt;

}
