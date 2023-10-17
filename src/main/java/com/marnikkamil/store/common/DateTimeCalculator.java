package com.marnikkamil.store.common;

import java.time.Instant;
import java.util.Date;

import static java.time.temporal.ChronoUnit.MINUTES;

public final class DateTimeCalculator {

  private DateTimeCalculator() {
  }

  public static Date currentDatePlusMinutes(int minutes) {
    return Date.from(nowPlusMinutes(minutes));
  }

  public static Instant nowPlusMinutes(int minutes) {
    return Instant.now().plus(minutes, MINUTES);
  }

}