package com.marnikkamil.store.security.subject.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

interface SubjectRepository extends MongoRepository<Subject, ObjectId> {

  Optional<Subject> findByUsername(String username);

}
