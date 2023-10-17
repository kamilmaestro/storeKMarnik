package com.marnikkamil.store.order

import com.marnikkamil.store.SecurityContextProvider
import com.marnikkamil.store.order.domain.OrderFacade
import com.marnikkamil.store.order.dto.NewOrderDto
import com.marnikkamil.store.order.dto.OrderDto
import com.marnikkamil.store.samples.SampleSubjects
import spock.lang.Specification
import spock.lang.Unroll

class CreateOrderSpec extends Specification implements SampleSubjects, SecurityContextProvider {

  private OrderFacade orderFacade = new OrderFacade()

  void setup() {
    given: "$ADMIN is logged in"
      logInUser(ADMIN)
  }

  @Unroll
  def "should be able to create a new order" () {
    given: "$subject is logged in"
      logInUser(subject)
    when: "$subject creates a new order"
      OrderDto order = orderFacade.addOrder(new NewOrderDto(subject.userId))
    then: "order is created by $subject"
      order.id != null
      order.createdById == subject.userId
    where:
      subject << [ADMIN, JOHN]
  }

}
