package com.marnikkamil.store.samples

import com.marnikkamil.store.order.dto.NewOrderDto

trait SampleOrders {

  static final String ADMIN_ORDER_ID = "601eaf4f9c6d500012340002"
  static final String FIRST_JOHN_ORDER_ID = "60b7d0f98018a60001ec2ae2"
  static final String SECOND_JOHN_ORDER_ID = "615f6e160e5b5a0015067ae6"
  static final String KEVIN_ORDER_ID = "5f8c0ce3a8db8321202f1d22"

  private static final Map NEW_ORDER_DEFAULT_VALUES = [
      "id" : "60c0b37f0d15690001d1a1bc",
      "createdById" : "5f6dbb7c2f0da130128457be"
  ]

  static NewOrderDto newOrder(Map<String, Object> properties = [:]) {
    properties = NEW_ORDER_DEFAULT_VALUES + properties

    return new NewOrderDto(properties.id as String)
  }

}