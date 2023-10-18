package com.marnikkamil.store.order.domain;

import com.marnikkamil.store.order.exception.IncorrectAmountOfFoodException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class AmountOfFood {

  static final AmountOfFood ONE = new AmountOfFood(1);

  int amount;

  public AmountOfFood(Integer amount) {
    this.amount = getVerifiedAmountOfFood(amount);
  }

  public int getValue() {
    return this.amount;
  }

  private int getVerifiedAmountOfFood(Integer amountOfFood) {
    return Optional.ofNullable(amountOfFood)
        .filter(this::isNaturalNumber)
        .orElseThrow(() -> new IncorrectAmountOfFoodException(amountOfFood));
  }

  private boolean isNaturalNumber(int number) {
    return number > 0;
  }

}
