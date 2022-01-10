package com.example.crm.application;

import java.util.List;
import java.util.Map;

import com.example.crm.entity.Customer;

public interface CrmApplication {

	Customer findCustomerByIdentity(String identity);

	List<Customer> findAllByPage(int pageNo, int pageSize);

	Customer addCustomer(Customer customer);

	Customer updateCustomer(String identity, Customer customer);

	Customer patchCustomer(String identity, Map<String, Object> changes);

	Customer removeCustomerByIdentity(String identity);

}
