package com.marnikkamil.store.supplier.domain;

import com.marnikkamil.store.supplier.dto.AddSupplierDto;
import com.marnikkamil.store.supplier.exception.InvalidSupplierDataException;

import java.time.LocalDateTime;
import java.util.Optional;

final class SupplierCreator {
  private final static String PHONE_PATTERN = "^[0-9()\\-+]+$";

  Supplier from(AddSupplierDto addSupplier) {
    validateData(addSupplier);

    return Supplier.builder()
        .name(addSupplier.getName())
        .phoneNumber(addSupplier.getPhoneNumber())
        .address(addSupplier.getAddress())
        .createdAt(LocalDateTime.now())
        .build();
  }

  private void validateData(AddSupplierDto addSupplier) {
    validateName(addSupplier.getName());
    validatePhoneNumber(addSupplier.getPhoneNumber());
  }

  private void validatePhoneNumber(String supplierPhoneNumber) {
    Optional.ofNullable(supplierPhoneNumber)
        .filter(this::isNotBlank)
        .filter(number -> number.matches(PHONE_PATTERN))
        .orElseThrow(() -> new InvalidSupplierDataException("Can not add supplier with phone number: " + supplierPhoneNumber));
  }

  private void validateName(String supplierName) {
    Optional.ofNullable(supplierName)
        .filter(this::isNotBlank)
        .orElseThrow(() -> new InvalidSupplierDataException("Can not add supplier with name: " + supplierName));
  }

  private boolean isNotBlank(String toCheck) {
    return !toCheck.isEmpty() && !toCheck.chars().allMatch(Character::isWhitespace);
  }

}
