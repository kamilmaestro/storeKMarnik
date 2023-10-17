package com.marnikkamil.store.order.infrastructure;

import com.marnikkamil.store.order.domain.OrderFacade;
import com.marnikkamil.store.order.dto.NewOrderDto;
import com.marnikkamil.store.order.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class OrderController {

  private final OrderFacade orderFacade;

  @Autowired
  public OrderController(OrderFacade orderFacade) {
    this.orderFacade = orderFacade;
  }

  @PostMapping("/")
  ResponseEntity<OrderDto> createOrder(@RequestBody NewOrderDto newOrder) {
    return ResponseEntity.ok(orderFacade.addOrder(newOrder));
  }

  @GetMapping("/{createdBy}")
  ResponseEntity<Page<OrderDto>> listOrders(@PathVariable String createdBy) {
    final Page<OrderDto> orders = createdBy == null ?
        orderFacade.listOrders()
        : orderFacade.listOrdersCreatedBy(createdBy);
    return ResponseEntity.ok(orders);
  }

  @PutMapping("/delete/{orderId}")
  ResponseEntity<Void> deleteOrder(@PathVariable String id) {
    orderFacade.deleteOrder(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
