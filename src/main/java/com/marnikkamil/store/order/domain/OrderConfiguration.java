package com.marnikkamil.store.order.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfiguration {

  OrderFacade orderFacade() {
    return orderFacade(new InMemoryOrderRepository());
  }

  @Bean
  OrderFacade orderFacade(OrderRepository orderRepository) {
    return new OrderFacade(orderRepository);
  }

}
