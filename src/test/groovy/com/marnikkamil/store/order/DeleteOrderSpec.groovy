package com.marnikkamil.store.order

import com.marnikkamil.store.SecurityContextProvider
import com.marnikkamil.store.order.domain.OrderFacade
import com.marnikkamil.store.order.dto.NewOrderDto
import com.marnikkamil.store.order.dto.OrderDto
import com.marnikkamil.store.samples.SampleOrders
import com.marnikkamil.store.samples.SampleSubjects
import spock.lang.Specification

class DeleteOrderSpec extends Specification implements SampleOrders, SampleSubjects, SecurityContextProvider {

  private OrderFacade orderFacade = new OrderFacade()

  void setup() {
    given: "$ADMIN is logged in"
      logInUser(ADMIN)
  }

  def "admin should delete order" () {
    given: "there is an order"
      OrderDto order = orderFacade.addOrder(new NewOrderDto(ADMIN_ORDER_ID))
    when: "deletes order"
      orderFacade.deleteOrder(order.id)
    then: "order does not exist anymore"
      orderFacade.listOrders().isEmpty()
  }

  def "customer should not be able to delete an order" () {
    given: "there is an order"
      OrderDto order = orderFacade.addOrder(new NewOrderDto(ADMIN_ORDER_ID))
    when: "deletes order"
      orderFacade.deleteOrder(order.id)
    then: "order does not exist anymore"
      orderFacade.listOrders().isEmpty()
  }

}
