package com.marnikkamil.store.order.domain;

import com.marnikkamil.store.common.LoggedUserGetter;
import com.marnikkamil.store.order.dto.NewOrderDto;
import com.marnikkamil.store.order.exception.InvalidOrderDataException;
import com.marnikkamil.store.supplier.domain.SupplierFacade;
import com.marnikkamil.store.supplier.dto.FoodDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class OrderCreator {

  SupplierFacade supplierFacade;

  Order from(NewOrderDto newOrder) {
    if (newOrder == null) {
      throw new InvalidOrderDataException();
    }
    final Map<String, FoodDto> existingFoodByIds = getSupplierFoodByIds(newOrder);
    final List<OrderedFood> orderedFood = newOrder.getFood().stream()
        .filter(food -> existingFoodByIds.containsKey(food.getFoodId()))
        .map(food -> {
          final FoodDto existingFood = existingFoodByIds.get(food.getFoodId());
          return new OrderedFood(food.getFoodId(), new AmountOfFood(food.getAmountOfFood()), existingFood.getPrice());
        }).collect(Collectors.toList());
    if (orderedFood.isEmpty()) {
      throw new InvalidOrderDataException();
    }

    return new Order(newOrder.getId(), LoggedUserGetter.getLoggedUserId(), orderedFood);
  }

  private Map<String, FoodDto> getSupplierFoodByIds(NewOrderDto newOrder) {
    return supplierFacade.getSupplierMenu(newOrder.getSupplierId())
        .getMenu()
        .stream()
        .collect(Collectors.toMap(FoodDto::getId, Function.identity()));
  }

}