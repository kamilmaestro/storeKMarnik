package com.marnikkamil.store.order.domain;

import com.marnikkamil.store.common.LoggedUserGetter;
import com.marnikkamil.store.order.dto.NewOrderDto;
import com.marnikkamil.store.order.dto.OrderDto;
import com.marnikkamil.store.order.exception.InsufficientPermissionsException;
import com.marnikkamil.store.order.exception.InvalidOrderDataException;
import com.marnikkamil.store.supplier.domain.SupplierFacade;
import com.marnikkamil.store.supplier.dto.FoodDto;
import com.marnikkamil.store.supplier.dto.SupplierMenuDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderFacade {

  OrderRepository orderRepository;
  OrderCreator orderCreator;

  public OrderDto addOrder(NewOrderDto newOrder) {
    final Order order = orderCreator.from(newOrder);
    return orderRepository.save(order)
        .dto();
  }

  public Page<OrderDto> listOrders() {
    return LoggedUserGetter.isAdmin() ?
        orderRepository.findAll(PageRequest.of(0, 10)).map(Order::dto)
        : listOrdersCreatedBy(LoggedUserGetter.getLoggedUserId());
  }

  public Page<OrderDto> listOrdersCreatedBy(String userId) {
    if (!LoggedUserGetter.isAdmin() && !LoggedUserGetter.getLoggedUserId().equals(userId)) {
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
