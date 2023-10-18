package com.marnikkamil.store.supplier.domain;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

interface SupplierRepository extends MongoRepository<Supplier, ObjectId> {

}
