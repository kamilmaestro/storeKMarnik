package com.marnikkamil.store.samples

import com.marnikkamil.store.supplier.dto.AddFoodToMenuDto

trait SampleFood {

  static final String FAKE_FOOD_ID = "5fbc3c7e036c370012345678"
  AddFoodToMenuDto PIZZA = AddFoodToMenuDto.builder().name("Pizza").price(20).build()

  private static final Map NEW_FOOD_DEFAULT_VALUES = [
      "name" : "Food",
      "supplierId" : "123456789",
      "price" : "10.00"
  ]

  static AddFoodToMenuDto newFood(Map<String, Object> properties = [:]) {
    properties = NEW_FOOD_DEFAULT_VALUES + properties

    return AddFoodToMenuDto.builder()
        .name(properties.name as String)
        .supplierId(properties.supplierId as String)
        .price(properties.price as Double)
        .build()
  }

}
