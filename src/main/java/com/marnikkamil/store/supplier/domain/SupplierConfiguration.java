package com.marnikkamil.store.supplier.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierConfiguration {

  public SupplierFacade supplierFacade() {
    return SupplierFacade.builder()
        .supplierRepository(new InMemorySupplierRepository())
        .foodRepository(new InMemoryFoodRepository())
        .supplierCreator(new SupplierCreator())
        .foodCreator(new FoodCreator())
        .build();
  }

  @Bean
  SupplierFacade supplierFacade(SupplierRepository supplierRepository, FoodRepository foodRepository) {
    return SupplierFacade.builder()
        .supplierRepository(supplierRepository)
        .foodRepository(foodRepository)
        .supplierCreator(new SupplierCreator())
        .foodCreator(new FoodCreator())
        .build();
  }

}
