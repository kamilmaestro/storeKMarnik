package com.marnikkamil.store.order.domain

import com.marnikkamil.store.SecurityContextProvider
import com.marnikkamil.store.order.dto.NewOrderDto
import com.marnikkamil.store.order.dto.OrderDto
import com.marnikkamil.store.order.dto.FoodToOrderDto
import com.marnikkamil.store.order.exception.InvalidOrderDataException
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

class CreateOrderSpec extends Specification implements SampleOrders, SampleSuppliers, SampleFood, SampleSubjects, SecurityContextProvider {

  private SupplierFacade supplierFacade = new SupplierConfiguration().supplierFacade()
  private OrderFacade orderFacade = new OrderConfiguration().orderFacade(supplierFacade)
  private SupplierDto pizzaSupplier
  private FoodDto food

  void setup() {
    given: "$ADMIN is logged in"
      logInUser(ADMIN)
    and: "there is a supplier $PIZZA_RESTAURANT with food"
      pizzaSupplier = supplierFacade.addSupplier(newSupplier(name: PIZZA_RESTAURANT.name))
      food = supplierFacade.addFoodToSupplierMenu(
          newFood(supplierId: pizzaSupplier.id, name: PIZZA.name, price: PIZZA.price)
      )
  }

  @Unroll
  def "should create a new order" () {
    given: "$subject is logged in"
      logInUser(subject)
    when: "$subject creates a new order"
      OrderDto order = orderFacade.addOrder(
          newOrder(orderId, pizzaSupplier.id, food.id)
      )
    then: "order is created by $subject"
      order.id != null
      order.createdById == subject.userId
    where:
      subject << [ADMIN, JOHN]
      orderId << [ADMIN_ORDER_ID, FIRST_JOHN_ORDER_ID]
  }

  def "should not create a new order with invalid data" () {
    when: "$ADMIN creates a new order without proper data"
      orderFacade.addOrder(null)
    then: "order is not created"
      thrown(InvalidOrderDataException)
  }

}
