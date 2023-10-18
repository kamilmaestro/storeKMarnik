package com.marnikkamil.store.supplier.domain;

import org.bson.types.ObjectId;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class InMemorySupplierRepository implements SupplierRepository {

  private final Map<ObjectId, Supplier> values = new ConcurrentHashMap<>();

  @Override
  public Supplier save(Supplier supplier) {
    if (supplier.getId() == null) {
      supplier.setId(new ObjectId());
    }
    values.put(supplier.getId(), supplier);
    return supplier;
  }

  @Override
  public <S extends Supplier> List<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<Supplier> findById(ObjectId objectId) {
    return Optional.ofNullable(values.get(objectId));
  }

  @Override
  public boolean existsById(ObjectId objectId) {
    return false;
  }

  @Override
  public List<Supplier> findAll() {
    return null;
  }

  @Override
  public Iterable<Supplier> findAllById(Iterable<ObjectId> objectIds) {
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
  public void delete(Supplier entity) {

  }

  @Override
  public void deleteAllById(Iterable<? extends ObjectId> objectIds) {

  }

  @Override
  public void deleteAll(Iterable<? extends Supplier> entities) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public List<Supplier> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<Supplier> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Supplier> S insert(S entity) {
    return null;
  }

  @Override
  public <S extends Supplier> List<S> insert(Iterable<S> entities) {
    return null;
  }

  @Override
  public <S extends Supplier> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends Supplier> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends Supplier> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends Supplier> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Supplier> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends Supplier> boolean exists(Example<S> example) {
    return false;
  }

  @Override
  public <S extends Supplier, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
    return null;
  }

}
