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
    Supplier toSave = supplierCreator.from(addSupplier);
    return supplierRepository.save(toSave).dto();
  }

  public SupplierDto getSupplierDto(String supplierId) {
    return getSupplier(new ObjectId(supplierId)).dto();
  }

  public FoodDto addFoodToSupplierMenu(AddFoodToMenuDto foodToAdd) {
    checkIfSupplierExists(foodToAdd.getSupplierId());
    Food food = foodCreator.from(foodToAdd);

    return foodRepository.save(food).dto();
  }

  public SupplierMenuDto getSupplierMenu(String supplierId) {
    final Supplier supplier = getSupplier(new ObjectId(supplierId));
    final List<Food> supplierFood = foodRepository.findAllBySupplierId(supplier.getId());

    return supplier.menuDto(supplierFood);
  }

//  public Page<SupplierDto> findAllSuppliers(PageInfo pageInfo) {
//    return supplierRepository.findAll(pageInfo.toPageRequest())
//        .map(Supplier::dto);
//  }

//  public void checkIfFoodExists(Collection<String> foodIds, String supplierId) {
//    getSupplier(new ObjectId(supplierId));
//    final long foundFoodSize = foodRepository.findAllById(foodIds).stream()
//        .filter(food -> food.getSupplierId().equals(supplierId))
//        .count();
//    if (foundFoodSize != foodIds.size()) {
//      throw new FoodNotFound("Can not find food");
//    }
//  }
//
  public void checkIfSupplierExists(String supplierId) {
    getSupplier(new ObjectId(supplierId));
  }

//  public List<SupplierDto> getSuppliersByIds(Collection<Long> supplierIds) {
//    return supplierRepository.findAllById(supplierIds).stream()
//        .map(Supplier::dto)
//        .collect(toList());
//  }

//  public List<FoodDto> getFoodByIds(Collection<String> foodIds) {
//    return foodRepository.findAllById(foodIds).stream()
//        .map(Food::dto)
//        .collect(toList());
//  }

//  public Page<FoodDto> getSupplierFood(long supplierId, PageInfo pageInfo) {
//    return foodRepository.findAllBySupplierId(supplierId, pageInfo.toPageRequest())
//        .map(Food::dto);
//  }

  private Supplier getSupplier(ObjectId supplierId) {
    return supplierRepository.findById(supplierId)
        .orElseThrow(SupplierNotFoundException::new);
  }

}
