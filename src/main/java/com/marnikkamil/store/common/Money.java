package com.marnikkamil.store.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.util.Optional;

import static java.math.RoundingMode.HALF_UP;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Money {

  private static final int DECIMAL_PLACES = 2;

  BigDecimal price;

  public Money(Double price) {
    this.price = Optional.ofNullable(price)
      .filter(this::isMoney)
      .map(BigDecimal::valueOf)
      .orElseThrow(() -> new InvalidPriceException(price));
  }

  public double getValueAsDouble() {
    return this.price.setScale(DECIMAL_PLACES, HALF_UP).doubleValue();
  }

  private boolean isMoney(double value) {
    return value > 0;
  }

}
