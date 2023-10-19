package com.marnikkamil.store.order.domain;

import com.marnikkamil.store.supplier.domain.SupplierFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfiguration {

  OrderFacade orderFacade(SupplierFacade supplierFacade) {
    return orderFacade(supplierFacade, new InMemoryOrderRepository());
  }

  @Bean
  OrderFacade orderFacade(SupplierFacade supplierFacade, OrderRepository orderRepository) {
    return new OrderFacade(orderRepository, new OrderCreator(supplierFacade));
  }

}
