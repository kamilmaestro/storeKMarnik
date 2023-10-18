package com.marnikkamil.store.supplier.domain

import com.marnikkamil.store.common.InvalidPriceException
import com.marnikkamil.store.samples.SampleFood
import com.marnikkamil.store.samples.SampleSuppliers
import com.marnikkamil.store.supplier.dto.FoodDto
import com.marnikkamil.store.supplier.dto.SupplierDto
import com.marnikkamil.store.supplier.exception.SupplierNotFoundException
import spock.lang.Specification
import spock.lang.Unroll

class AddFoodToMenuSpec extends Specification implements SampleSuppliers, SampleFood {

  private SupplierFacade supplierFacade = new SupplierConfiguration().supplierFacade()

  def "should be able to add food to the menu" () {
    given: "there is a supplier $PIZZA_RESTAURANT"
      SupplierDto supplier = supplierFacade.addSupplier(newSupplier(name: PIZZA_RESTAURANT.name))
    when: "adds a food to the menu of $PIZZA_RESTAURANT"
      FoodDto addedFood = supplierFacade.addFoodToSupplierMenu(
          newFood(supplierId: supplier.id, name: PIZZA.name, price: PIZZA.price)
      )
    then: "menu of $PIZZA_RESTAURANT contains this food"
      addedFood.supplierId == supplier.id
      addedFood.name == PIZZA.name
      addedFood.price == PIZZA.price
  }

  def "should not add food to a non-existing supplier" () {
    when: "adds a food to the menu"
      supplierFacade.addFoodToSupplierMenu(newFood(supplierId: FAKE_SUPPLIER_ID))
    then: "food is not added because supplier can not be found"
      thrown(SupplierNotFoundException)
  }

  @Unroll
  def "should not add a food with the wrong price" () {
    given: "there is a supplier"
      SupplierDto supplier = supplierFacade.addSupplier(newSupplier())
    when: "wants to add a food to the menu of this supplier"
      supplierFacade.addFoodToSupplierMenu(newFood(supplierId: supplier.id, price: price))
    then: "food is not added due to wrong price"
      thrown(InvalidPriceException)
    where:
      price << [0, -1, null]
  }

  @Unroll
  def "should store price of food in a value with at most two decimal places" () {
    given: "there is a supplier"
      SupplierDto supplier = supplierFacade.addSupplier(newSupplier())
    expect: "added food has price with at most two decimal places"
      supplierFacade.addFoodToSupplierMenu(newFood(supplierId: supplier.id, price: price))
          .price == returnedValue
    where:
      price   ||  returnedValue
      0.5555  ||  0.56
      2.123   ||  2.12
      1       ||  1
      10.0091 ||  10.01
      4.0008  ||  4
      21.00   ||  21
  }

}
