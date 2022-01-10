package com.example.crm.application.business;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.crm.application.CrmApplication;
import com.example.crm.application.business.exception.CustomerAlreadyExistException;
import com.example.crm.application.business.exception.CustomerNotFoundException;
import com.example.crm.entity.Customer;
import com.example.crm.repository.CustomerJpaRepository;

@Service
public class StandardCrmApplication implements CrmApplication {
	private CustomerJpaRepository customerJpaRepository;
	
	public StandardCrmApplication(CustomerJpaRepository customerJpaRepository) {
		this.customerJpaRepository = customerJpaRepository;
	}

	@Override
	public Customer findCustomerByIdentity(String identity) {
		return customerJpaRepository.findById(identity)
				                    .orElseThrow(() -> new CustomerNotFoundException());
	}

	@Override
	public List<Customer> findAllByPage(int pageNo, int pageSize) {
		var page = PageRequest.of(pageNo, pageSize);
		return customerJpaRepository.findAll(page).getContent();
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.NEVER)
	public Customer addCustomer(Customer customer) {
		var identity = customer.getIdentity();
		if (customerJpaRepository.existsById(identity))
			throw new CustomerAlreadyExistException();
		return customerJpaRepository.save(customer);                     
	}

	@Override
	public Customer updateCustomer(String identity, Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer patchCustomer(String identity, Map<String, Object> changes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer removeCustomerByIdentity(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

}
