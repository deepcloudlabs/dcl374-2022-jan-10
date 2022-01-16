package com.example.hr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hr.document.EmployeeDocument;

public interface EmployeeMongoRepository extends MongoRepository<EmployeeDocument, String>{

}
