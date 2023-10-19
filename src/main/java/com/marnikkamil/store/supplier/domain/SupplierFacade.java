package com.marnikkamil.store.supplier.domain;

import com.marnikkamil.store.supplier.dto.*;
import com.marnikkamil.store.supplier.exception.SupplierNotFoundException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;

import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupplierFacade {

  SupplierRepository supplierRepository;
  FoodRepository foodRepository;
  SupplierCreator supplierCreator;
  FoodCreator foodCreator;

  public SupplierDto addSupplier(AddSupplierDto addSupplier) {
    final Supplier toSave = supplierCreator.from(addSupplier);
    return supplierRepository.save(toSave).dto();
  }

  public SupplierDto getSupplierDto(String supplierId) {
    return getSupplier(new ObjectId(supplierId)).dto();
  }

  public FoodDto addFoodToSupplierMenu(AddFoodToMenuDto foodToAdd) {
    checkIfSupplierExists(foodToAdd.getSupplierId());
    final Food food = foodCreator.from(foodToAdd);

    return foodRepository.save(food).dto();
  }

  public SupplierMenuDto getSupplierMenu(String supplierId) {
    final Supplier supplier = getSupplier(new ObjectId(supplierId));
    final List<Food> supplierFood = foodRepository.findAllBySupplierId(supplier.getId());

    return supplier.menuDto(supplierFood);
  }

  private void checkIfSupplierExists(String supplierId) {
    getSupplier(new ObjectId(supplierId));
  }

  private Supplier getSupplier(ObjectId supplierId) {
    return supplierRepository.findById(supplierId)
        .orElseThrow(SupplierNotFoundException::new);
  }

}
