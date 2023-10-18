package com.marnikkamil.store.order.domain;

import com.marnikkamil.store.common.Money;
import com.marnikkamil.store.order.dto.FoodToOrderDto;
import com.marnikkamil.store.order.dto.OrderedFoodDto;
import org.bson.types.ObjectId;

class OrderedFood {

  ObjectId foodId;
  AmountOfFood amount;
  Money price;

  OrderedFood(String foodId, AmountOfFood amount, double price) {
    this.foodId = new ObjectId(foodId);
    this.amount = amount;
    this.price = new Money(price);
  }

  OrderedFoodDto dto() {
    return OrderedFoodDto.builder()
        .foodId(this.foodId.toString())
        .amountOfFood(this.amount.getValue())
        .price(this.price.getValueAsDouble())
        .build();
  }

  void edit(int amount, double price) {
    this.amount = new AmountOfFood(amount);
    this.price = new Money(price);
  }

}
