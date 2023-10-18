package com.marnikkamil.store.supplier.domain

import com.marnikkamil.store.samples.SampleFood
import com.marnikkamil.store.samples.SampleSuppliers
import com.marnikkamil.store.supplier.dto.SupplierDto
import com.marnikkamil.store.supplier.exception.InvalidSupplierDataException
import spock.lang.Specification
import spock.lang.Unroll

class AddSupplierSpec extends Specification implements SampleSuppliers, SampleFood {

  private SupplierFacade supplierFacade = new SupplierConfiguration().supplierFacade()

  def "should add a new supplier" () {
    when: "wants to add a new supplier"
      SupplierDto supplier = supplierFacade.addSupplier(newSupplier())
    then: "there is a supplier"
      supplierFacade.getSupplierDto(supplier.id) == supplier
  }

  @Unroll
  def "should not add a supplier with an invalid name" () {
    when: "wants to add a new supplier"
      supplierFacade.addSupplier(newSupplier(name: data))
    then: "supplier is not added due to wrong name"
      thrown(InvalidSupplierDataException)
    where:
      data << [null, "", "  "]
  }

  @Unroll
  def "should not add a supplier with an invalid phone number" () {
    when: "wants to add a new supplier"
      supplierFacade.addSupplier(newSupplier(phoneNumber: data))
    then: "supplier is not added due to wrong phone number"
      thrown(InvalidSupplierDataException)
    where:
      data << [null, "", "  "]
  }

}
