package com.marnikkamil.store.order.domain

import com.marnikkamil.store.SecurityContextProvider
import com.marnikkamil.store.order.domain.OrderFacade
import com.marnikkamil.store.order.dto.NewOrderDto
import com.marnikkamil.store.order.dto.OrderDto
import com.marnikkamil.store.order.exception.InsufficientPermissionsException
import com.marnikkamil.store.samples.SampleFood
import com.marnikkamil.store.samples.SampleOrders
import com.marnikkamil.store.samples.SampleSubjects
import com.marnikkamil.store.samples.SampleSuppliers
import com.marnikkamil.store.supplier.domain.SupplierConfiguration
import com.marnikkamil.store.supplier.domain.SupplierFacade
import com.marnikkamil.store.supplier.dto.FoodDto
import com.marnikkamil.store.supplier.dto.SupplierDto
import spock.lang.Specification
import spock.lang.Unroll

class ListOrderSpec extends Specification implements SampleOrders, SampleSuppliers, SampleFood, SampleSubjects, SecurityContextProvider {

  private SupplierFacade supplierFacade = new SupplierConfiguration().supplierFacade()
  private OrderFacade orderFacade = new OrderConfiguration().orderFacade(supplierFacade)
  private OrderDto firstJohnOrder
  private OrderDto secondJohnOrder
  private OrderDto kevinOrder
  private OrderDto adminOrder
  private SupplierDto pizzaSupplier
  private FoodDto food

  void setup() {
    given: "there is a supplier $PIZZA_RESTAURANT with food"
      logInUser(ADMIN)
      pizzaSupplier = supplierFacade.addSupplier(newSupplier(name: PIZZA_RESTAURANT.name))
      food = supplierFacade.addFoodToSupplierMenu(
          newFood(supplierId: pizzaSupplier.id, name: PIZZA.name, price: PIZZA.price)
      )
    and: "there are two orders created by $JOHN"
      logInUser(JOHN)
      firstJohnOrder = orderFacade.addOrder(newOrder(FIRST_JOHN_ORDER_ID, pizzaSupplier.id, food.id))
      secondJohnOrder = orderFacade.addOrder(newOrder(SECOND_JOHN_ORDER_ID, pizzaSupplier.id, food.id))
    and: "there is an order created by $KEVIN"
      logInUser(KEVIN)
      kevinOrder = orderFacade.addOrder(newOrder(KEVIN_ORDER_ID, pizzaSupplier.id, food.id))
    and: "there is an order created by $ADMIN"
      logInUser(ADMIN)
      adminOrder = orderFacade.addOrder(newOrder(ADMIN_ORDER_ID, pizzaSupplier.id, food.id))
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
  def "admin should list orders created by a specified user" () {
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
