package com.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crm.entity.Customer;

public interface CustomerJpaRepository extends JpaRepository<Customer, String>{

}
