package com.marnikkamil.store.order.domain

import com.marnikkamil.store.SecurityContextProvider
import com.marnikkamil.store.order.dto.NewOrderDto
import com.marnikkamil.store.order.dto.OrderDto
import com.marnikkamil.store.samples.SampleOrders
import com.marnikkamil.store.samples.SampleSubjects
import spock.lang.Specification
import spock.lang.Unroll

class CreateOrderSpec extends Specification implements SampleOrders, SampleSubjects, SecurityContextProvider {

  private OrderFacade orderFacade = new OrderConfiguration().orderFacade()

  void setup() {
    given: "$ADMIN is logged in"
      logInUser(ADMIN)
  }

  @Unroll
  def "should create a new order" () {
    given: "$subject is logged in"
      logInUser(subject)
    when: "$subject creates a new order"
      OrderDto order = orderFacade.addOrder(new NewOrderDto(orderId))
    then: "order is created by $subject"
      order.id != null
      order.createdById == subject.userId
    where:
      subject << [ADMIN, JOHN]
      orderId << [ADMIN_ORDER_ID, FIRST_JOHN_ORDER_ID]
  }

  def "should create a new order with generated identifier when invalid data is passed" () {
    when: "$ADMIN creates a new order without proper data"
      OrderDto order = orderFacade.addOrder(null)
    then: "order is created by $ADMIN"
      order.id != null
      order.createdById == ADMIN.userId
  }

}
