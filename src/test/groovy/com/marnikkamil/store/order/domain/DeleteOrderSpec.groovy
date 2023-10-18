package com.marnikkamil.store.order.domain

import com.marnikkamil.store.SecurityContextProvider
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

class DeleteOrderSpec extends Specification implements SampleOrders, SampleSuppliers, SampleFood, SampleSubjects, SecurityContextProvider {

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

  def "admin should delete order" () {
    given: "there is an order"
      OrderDto order = orderFacade.addOrder(newOrder(ADMIN_ORDER_ID, pizzaSupplier.id, food.id))
    when: "deletes order"
      orderFacade.deleteOrder(order.id)
    then: "order does not exist anymore"
      orderFacade.listOrders().isEmpty()
  }

  def "customer should not be able to delete an order" () {
    given: "there is an order created by $KEVIN"
      logInUser(KEVIN)
      OrderDto order = orderFacade.addOrder(newOrder(ADMIN_ORDER_ID, pizzaSupplier.id, food.id))
    when: "$KEVIN deletes his order"
      orderFacade.deleteOrder(order.id)
    then: "$KEVIN does not have permissions to delete orders"
      thrown(InsufficientPermissionsException)
  }

}
