package com.marnikkamil.store.order.domain;

import com.marnikkamil.store.common.LoggedUserGetter;
import com.marnikkamil.store.order.dto.NewOrderDto;
import com.marnikkamil.store.order.dto.OrderDto;
import com.marnikkamil.store.order.exception.InsufficientPermissionsException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderFacade {

  OrderRepository orderRepository;

  public OrderDto addOrder(NewOrderDto newOrder) {
    if (newOrder == null) {
      newOrder = NewOrderDto.withNewId();
    }
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
      throw InsufficientPermissionsException.canNotListOrdersCreatedByOthers(LoggedUserGetter.getLoggedUserId());
    }
    return orderRepository.findAllByCreatedById(new ObjectId(userId), PageRequest.of(0, 10))
        .map(Order::dto);
  }

  public void deleteOrder(String orderId) {
    if (!LoggedUserGetter.isAdmin()) {
      throw InsufficientPermissionsException.canNotDelete(LoggedUserGetter.getLoggedUserId());
    }
    orderRepository.deleteById(new ObjectId(orderId));
  }

}
