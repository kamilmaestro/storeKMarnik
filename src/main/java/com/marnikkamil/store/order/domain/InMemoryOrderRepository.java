package com.marnikkamil.store.order.domain;

import org.bson.types.ObjectId;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

final class InMemoryOrderRepository implements OrderRepository {

  private final Map<ObjectId, Order> values = new ConcurrentHashMap<>();

  @Override
  public Order save(Order order) {
    if (order.getId() == null) {
      order.setId(new ObjectId());
    }
    values.put(order.getId(), order);

    return order;
  }

  @Override
  public <S extends Order> List<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<Order> findById(ObjectId objectId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean existsById(ObjectId objectId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Order> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterable<Order> findAllById(Iterable<ObjectId> objectIds) {
    throw new UnsupportedOperationException();
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteById(ObjectId objectId) {
    values.remove(objectId);
  }

  @Override
  public void delete(Order entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteAllById(Iterable<? extends ObjectId> objectIds) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteAll(Iterable<? extends Order> entities) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Order> findAll(Sort sort) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Page<Order> findAll(Pageable pageable) {
    return new PageImpl<>(
        values.values().stream().collect(Collectors.toList())
    );
  }

  @Override
  public <S extends Order> S insert(S entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <S extends Order> List<S> insert(Iterable<S> entities) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <S extends Order> Optional<S> findOne(Example<S> example) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <S extends Order> List<S> findAll(Example<S> example) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <S extends Order> long count(Example<S> example) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <S extends Order> boolean exists(Example<S> example) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <S extends Order, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Page<Order> findAllByCreatedById(ObjectId createdById, Pageable pageable) {
    return new PageImpl<>(
        values.values().stream()
            .filter(order -> order.getCreatedById().equals(createdById))
            .collect(Collectors.toList())
    );
  }

}
