package com.marnikkamil.store.samples

import com.marnikkamil.store.supplier.domain.SupplierFacade
import com.marnikkamil.store.supplier.dto.AddSupplierDto
import com.marnikkamil.store.supplier.dto.SupplierDto

trait SampleSuppliers {

  static String FAKE_SUPPLIER_ID = "60e0b5f3f34e6900123d51e6"
  private static final String PIZZA_RESTAURANT_ID = "60d7f1920f9e870012bd534ab"
  private static final String KEBAB_RESTAURANT_ID = "5f972d81d2c6a209ab34723f"
  private static final String APPLE_RESTAURANT_ID = "602c5b4f4bb12400120d634f"

  private static final Map NEW_SUPPLIER_DEFAULT_VALUES = [
      "name" : "Supplier",
      "phoneNumber" : "123456789",
      "address" : "Warsaw"
  ]

  static SupplierDto PIZZA_RESTAURANT = createSupplierDto(PIZZA_RESTAURANT_ID, "Pizza Restaurant")
  static SupplierDto KEBAB_RESTAURANT = createSupplierDto(KEBAB_RESTAURANT_ID, "Kebab Restaurant")
  static SupplierDto APPLE_RESTAURANT = createSupplierDto(APPLE_RESTAURANT_ID, "Apple Restaurant")

  static void withSampleSuppliers(SupplierFacade supplierFacade, SupplierDto ...suppliers) {
    suppliers.each { supplier ->
      supplierFacade.addSupplier(newSupplier(name: supplier.name))}
  }

  static AddSupplierDto newSupplier(Map<String, Object> properties = [:]) {
    properties = NEW_SUPPLIER_DEFAULT_VALUES + properties

    return AddSupplierDto.builder()
        .name(properties.name as String)
        .phoneNumber(properties.phoneNumber as String)
        .address(properties.address as String)
        .build()
  }

  private static SupplierDto createSupplierDto(String id, String name) {
    return SupplierDto.builder()
        .id(id)
        .name(name)
        .build()
  }

}