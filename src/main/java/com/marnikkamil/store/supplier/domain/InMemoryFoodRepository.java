package com.marnikkamil.store.supplier.domain;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class InMemoryFoodRepository implements FoodRepository {

  private final Map<ObjectId, Food> values = new ConcurrentHashMap<>();


  @Override
  public List<Food> findAllBySupplierId(ObjectId supplierId) {
    return values.values().stream()
        .filter(food -> food.getSupplierId().equals(supplierId))
        .collect(Collectors.toList());
  }

  @Override
  public Page<Food> findAllBySupplierId(ObjectId supplierId, Pageable pageable) {
    return null;
  }

  @Override
  public Food save(Food food) {
    if (food.getId() == null) {
      food.setId(new ObjectId());
    }
    values.put(food.getId(), food);
    return food;
  }

  @Override
  public <S extends Food> List<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<Food> findById(ObjectId objectId) {
    return Optional.ofNullable(values.get(objectId));
  }

  @Override
  public boolean existsById(ObjectId objectId) {
    return false;
  }

  @Override
  public List<Food> findAll() {
    return null;
  }

  @Override
  public Iterable<Food> findAllById(Iterable<ObjectId> objectIds) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(ObjectId objectId) {

  }

  @Override
  public void delete(Food entity) {

  }

  @Override
  public void deleteAllById(Iterable<? extends ObjectId> objectIds) {

  }

  @Override
  public void deleteAll(Iterable<? extends Food> entities) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public List<Food> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<Food> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Food> S insert(S entity) {
    return null;
  }

  @Override
  public <S extends Food> List<S> insert(Iterable<S> entities) {
    return null;
  }

  @Override
  public <S extends Food> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends Food> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends Food> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends Food> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Food> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends Food> boolean exists(Example<S> example) {
    return false;
  }

  @Override
  public <S extends Food, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
    return null;
  }

}
