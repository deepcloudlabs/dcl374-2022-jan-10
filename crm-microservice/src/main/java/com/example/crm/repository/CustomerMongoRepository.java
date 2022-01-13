package com.example.crm.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crm.document.CustomerDocument;

public interface CustomerMongoRepository extends MongoRepository<CustomerDocument, String>{
	List<CustomerDocument> findAllByBirthYearBetweenAndAddressesCountry(
			int fromYear, int toYear,String country);
}
