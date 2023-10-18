package com.marnikkamil.store.supplier.infrastructure;

import com.marnikkamil.store.supplier.domain.SupplierFacade;
import com.marnikkamil.store.supplier.dto.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/supplier")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SupplierController {

  SupplierFacade supplierFacade;

  @Autowired
  public SupplierController(@Autowired SupplierFacade supplierFacade) {
    this.supplierFacade = supplierFacade;
  }

  @PostMapping("/")
  public ResponseEntity<SupplierDto> addSupplier(@RequestBody AddSupplierDto addSupplier) {
    return ResponseEntity.ok(supplierFacade.addSupplier(addSupplier));
  }

//  @GetMapping("/")
//  public ResponseEntity<Page<SupplierDto>> findSuppliers(@ModelAttribute PageInfo pageInfo) {
//    return ResponseEntity.ok(supplierFacade.findAllSuppliers(pageInfo));
//  }

  @GetMapping("/{id}")
  public ResponseEntity<SupplierMenuDto> getSupplierMenu(@PathVariable("id") String id) {
    return ResponseEntity.ok(supplierFacade.getSupplierMenu(id));
  }

  @PostMapping("/food")
  public ResponseEntity<FoodDto> addFood(@RequestBody AddFoodToMenuDto addFoodToMenu) {
    return ResponseEntity.ok(supplierFacade.addFoodToSupplierMenu(addFoodToMenu));
  }

//  @PostMapping("/ids")
//  public ResponseEntity<List<SupplierDto>> getSuppliersByIds(@RequestBody List<Long> supplierIds) {
//    return ResponseEntity.ok(supplierFacade.getSuppliersByIds(supplierIds));
//  }

//  @GetMapping("/{id}/food")
//  public ResponseEntity<Page<FoodDto>> getSupplierFood(@PathVariable("id") long supplierId,
//                                                       @ModelAttribute PageInfo pageInfo) {
//    return ResponseEntity.ok(supplierFacade.getSupplierFood(supplierId, pageInfo));
//  }

//  @PostMapping("/food/ids")
//  public ResponseEntity<List<FoodDto>> getFoodByIds(@RequestBody List<Long> foodIds) {
//    return ResponseEntity.ok(supplierFacade.getFoodByIds(foodIds));
//  }
//
//  @GetMapping("/search")
//  public ResponseEntity<Page<SupplierDto>> searchSuppliers(@RequestParam("text") String searchText,
//                                                           @ModelAttribute PageInfo pageInfo) {
//    return ResponseEntity.ok(supplierFacade.searchSuppliers(searchText, pageInfo));
//  }

}
