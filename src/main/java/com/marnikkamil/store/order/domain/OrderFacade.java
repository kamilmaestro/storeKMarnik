package com.marnikkamil.store.order.domain;

import com.marnikkamil.store.common.LoggedUserGetter;
import com.marnikkamil.store.order.dto.NewOrderDto;
import com.marnikkamil.store.order.dto.OrderDto;
import com.marnikkamil.store.order.exception.InsufficientPermissionsException;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class OrderFacade {

  private final OrderRepository orderRepository;

  public OrderFacade(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public OrderFacade() {
    this.orderRepository = new InMemoryOrderRepository();
  }

  public OrderDto addOrder(NewOrderDto newOrder) {
    return orderRepository.save(new Order(newOrder.getId(), LoggedUserGetter.getLoggedUserId()))
        .dto();
  }

  public Page<OrderDto> listOrders() {
    return LoggedUserGetter.isAdmin() ?
        orderRepository.findAll(PageRequest.of(0, 10)).map(Order::dto)
        : listOrdersCreatedBy(LoggedUserGetter.getLoggedUserId());
  }

  public Page<OrderDto> listOrdersCreatedBy(String userId) {
    if (!LoggedUserGetter.isAdmin()) {
      throw new InsufficientPermissionsException(userId);
    }
    return orderRepository.findAllByCreatedById(new ObjectId(userId), PageRequest.of(0, 10))
        .map(Order::dto);
  }

  public void deleteOrder(String orderId) {
    orderRepository.deleteById(new ObjectId(orderId));
  }

}
