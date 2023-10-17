package com.marnikkamil.store.order.domain;

import com.marnikkamil.store.order.dto.OrderDto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("order")
class Order {

  @MongoId
  private ObjectId id;
  private ObjectId createdById;

  Order(String id, String createdById) {
    this.id = new ObjectId(id);
    this.createdById = new ObjectId(createdById);
  }

  Order(String createdById) {
    this.createdById = new ObjectId(createdById);
  }

  OrderDto dto() {
    return new OrderDto(id.toString(), createdById.toString());
  }

  ObjectId getId() {
    return id;
  }

  ObjectId getCreatedById() {
    return createdById;
  }

  void setId(ObjectId id) {
    this.id = id;
  }

}
