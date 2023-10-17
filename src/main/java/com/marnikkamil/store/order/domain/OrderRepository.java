package com.marnikkamil.store.order.domain;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

interface OrderRepository extends MongoRepository<Order, ObjectId> {

  Page<Order> findAllByCreatedById(ObjectId createdById, Pageable pageable);

}
