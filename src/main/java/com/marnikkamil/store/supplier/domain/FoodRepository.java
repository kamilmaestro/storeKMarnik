package com.marnikkamil.store.supplier.domain;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

interface FoodRepository extends MongoRepository<Food, ObjectId> {

  List<Food> findAllBySupplierId(ObjectId supplierId);

  Page<Food> findAllBySupplierId(ObjectId supplierId, Pageable pageable);

}
