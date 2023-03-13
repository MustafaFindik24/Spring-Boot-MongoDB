package com.mustafafindik.nosql.repository;

import com.mustafafindik.nosql.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
}
