package com.example.crm.application.business;

import java.lang.reflect.Field;
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
        System.err.println(customerJpaRepository.getClass().getName());
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
	@Transactional(isolation = Isolation.DEFAULT)
	public Customer addCustomer(Customer customer) {
		var identity = customer.getIdentity();
		if (customerJpaRepository.existsById(identity))
			throw new CustomerAlreadyExistException();
		return customerJpaRepository.save(customer);                     
	}

	@Override
	@Transactional
	public Customer updateCustomer(String identity, Customer customer) {
		var managedCustomer = 
				customerJpaRepository.findById(identity)
				                     .orElseThrow( () -> new CustomerNotFoundException()) ;
		managedCustomer.setPhone(customer.getPhone());
		managedCustomer.setPhoto(customer.getPhoto());
		managedCustomer.setEmail(customer.getEmail());
		managedCustomer.setType(customer.getType());
		managedCustomer.setFullname(customer.getFullname());
		//managedCustomer.setAddresses(customer.getAddresses());
		customerJpaRepository.flush();
		return managedCustomer;
	}

	@Override
	@Transactional
	public Customer patchCustomer(String identity, Map<String, Object> changes) {
		var managedCustomer = 
				customerJpaRepository.findById(identity)
				                     .orElseThrow( () -> new CustomerNotFoundException()) ;
		for(var entry : changes.entrySet()) {
			var attribute = entry.getKey();
			var value = entry.getValue();
			System.err.println(entry);
			try {
				var field = Customer.class.getDeclaredField(attribute);
				field.setAccessible(true);
				field.set(managedCustomer, value);
				field.setAccessible(false);
			} catch (IllegalArgumentException | 
					IllegalAccessException | NoSuchFieldException | SecurityException e) {
				System.err.println(e.getMessage());
			}
		}
		return managedCustomer;
	}

	@Override
	@Transactional
	public Customer removeCustomerByIdentity(String identity) {
		var managedCustomer = 
				customerJpaRepository.findById(identity)
				                     .orElseThrow( () -> new CustomerNotFoundException()) ;
		customerJpaRepository.delete(managedCustomer);
		return managedCustomer;
	}

}
