package com.mustafafindik.nosql.Repository;

import com.mustafafindik.nosql.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {
}
