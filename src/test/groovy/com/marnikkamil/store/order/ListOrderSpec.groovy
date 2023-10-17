package com.marnikkamil.store.order

import com.marnikkamil.store.SecurityContextProvider
import com.marnikkamil.store.order.domain.OrderFacade
import com.marnikkamil.store.order.dto.NewOrderDto
import com.marnikkamil.store.order.dto.OrderDto
import com.marnikkamil.store.order.exception.InsufficientPermissionsException
import com.marnikkamil.store.samples.SampleOrders
import com.marnikkamil.store.samples.SampleSubjects
import spock.lang.Specification
import spock.lang.Unroll

class ListOrderSpec extends Specification implements SampleOrders, SampleSubjects, SecurityContextProvider {

  private OrderFacade orderFacade = new OrderFacade()
  private OrderDto firstJohnOrder
  private OrderDto secondJohnOrder
  private OrderDto kevinOrder
  private OrderDto adminOrder

  void setup() {
    given: "there are two orders created by $JOHN"
      logInUser(JOHN)
      firstJohnOrder = orderFacade.addOrder(new NewOrderDto(FIRST_JOHN_ORDER_ID))
      secondJohnOrder = orderFacade.addOrder(new NewOrderDto(SECOND_JOHN_ORDER_ID))
    and: "there is an order created by $KEVIN"
      logInUser(KEVIN)
      kevinOrder = orderFacade.addOrder(new NewOrderDto(KEVIN_ORDER_ID))
    and: "there is an order created by $ADMIN"
      logInUser(ADMIN)
      adminOrder = orderFacade.addOrder(new NewOrderDto(ADMIN_ORDER_ID))
  }

  @Unroll
  def "customer should list his own orders" () {
    given: "$subject is logged in"
      logInUser(subject)
    expect: "$subject gets his orders"
      orderFacade.listOrders().sort() == orders.sort()
    where:
      subject ||  orders
      JOHN    ||  [new OrderDto(FIRST_JOHN_ORDER_ID, JOHN.userId), new OrderDto(SECOND_JOHN_ORDER_ID, JOHN.userId)]
      KEVIN   ||  [new OrderDto(KEVIN_ORDER_ID, KEVIN.userId)]
      MARC    ||  []
  }

  def "admin should list all orders" () {
    expect: "$ADMIN gets all orders"
      orderFacade.listOrders().sort() == [firstJohnOrder, secondJohnOrder, kevinOrder, adminOrder].sort()
  }

  @Unroll
  def "admin should list orders of a specified user" () {
    expect: "$ADMIN gets orders created by $createdBy"
      orderFacade.listOrdersCreatedBy(createdBy.userId).sort() == orders.sort()
    where:
      createdBy ||  orders
      JOHN    ||  [new OrderDto(FIRST_JOHN_ORDER_ID, JOHN.userId), new OrderDto(SECOND_JOHN_ORDER_ID, JOHN.userId)]
      KEVIN   ||  [new OrderDto(KEVIN_ORDER_ID, KEVIN.userId)]
      MARC    ||  []
  }

  @Unroll
  def "customer should not list orders created by other users" () {
    given: "$subject is logged in"
      logInUser(subject)
    when: "$subject lists orders created by $createdBy"
      orderFacade.listOrdersCreatedBy(createdBy.userId)
    then: "$subject does not have permissions to view those orders"
      thrown(InsufficientPermissionsException)
    where:
      subject ||  createdBy
      JOHN    ||  KEVIN
      JOHN    ||  MARC
  }

}
