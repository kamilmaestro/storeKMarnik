package com.marnikkamil.store.order.domain;

import com.marnikkamil.store.order.dto.OrderDto;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Document("order")
class Order {

  @MongoId
  private ObjectId id;
  private ObjectId createdById;
  private List<OrderedFood> food;

  Order(String id, String createdById, List<OrderedFood> food) {
    this.id = new ObjectId(id);
    this.createdById = new ObjectId(createdById);
    this.food = food;
  }

  OrderDto dto() {
    return new OrderDto(id.toString(), createdById.toString());
  }

}
