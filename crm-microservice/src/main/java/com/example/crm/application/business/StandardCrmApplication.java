package com.example.crm.application.business;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bson.internal.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.crm.application.CrmApplication;
import com.example.crm.application.business.exception.CustomerAlreadyExistException;
import com.example.crm.application.business.exception.CustomerNotFoundException;
import com.example.crm.dto.request.AcquireCustomerRequest;
import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.AcquireCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.dto.response.DeleteCustomerResponse;
import com.example.crm.dto.response.DetailedCustomerResponse;
import com.example.crm.dto.response.PatchCustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;
import com.example.crm.entity.Address;
import com.example.crm.entity.Customer;
import com.example.crm.repository.CustomerJpaRepository;

@Service
@ConditionalOnProperty(name = "crm.persistence", havingValue = "relational")
public class StandardCrmApplication implements CrmApplication {
	private CustomerJpaRepository customerJpaRepository;
	private ModelMapper modelMapper;

	public StandardCrmApplication(CustomerJpaRepository customerJpaRepository, ModelMapper modelMapper) {
		this.customerJpaRepository = customerJpaRepository;
		this.modelMapper = modelMapper;
		System.err.println(customerJpaRepository.getClass().getName());
	}

	@Override
	public DetailedCustomerResponse findCustomerByIdentity(String identity) {
		var customer = customerJpaRepository.findById(identity).orElseThrow(() -> new CustomerNotFoundException());
		var detailedCustomerResponse = modelMapper.map(customer, DetailedCustomerResponse.class);
		if (Objects.nonNull(customer.getPhoto()))
		   detailedCustomerResponse.setPhoto(Base64.encode(customer.getPhoto()));
		else
			detailedCustomerResponse.setPhoto(null);
		return detailedCustomerResponse;
	}

	@Override
	public List<CustomerResponse> findAllByPage(int pageNo, int pageSize) {
		return customerJpaRepository.findAll(PageRequest.of(pageNo, pageSize)).getContent().stream()
				.map(cust -> modelMapper.map(cust, CustomerResponse.class)).toList();
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT)
	public AcquireCustomerResponse addCustomer(AcquireCustomerRequest request) {
		var identity = request.getIdentity();
		if (customerJpaRepository.existsById(identity))
			throw new CustomerAlreadyExistException();
		var customer = modelMapper.map(request, Customer.class);
		return modelMapper.map(customerJpaRepository.save(customer), AcquireCustomerResponse.class);
	}

	@Override
	@Transactional
	public UpdateCustomerResponse updateCustomer(String identity, UpdateCustomerRequest request) {
		var managedCustomer = customerJpaRepository.findById(identity)
				.orElseThrow(() -> new CustomerNotFoundException());
		managedCustomer.setPhone(request.getPhone());
		String photo = request.getPhoto();
		if (Objects.nonNull(photo))
		   managedCustomer.setPhoto(Base64.decode(photo));
		managedCustomer.setEmail(request.getEmail());
		managedCustomer.setType(request.getType());
		managedCustomer.setFullname(request.getFullname());
		managedCustomer.getAddresses().clear();
		managedCustomer.getAddresses().addAll(request.getAddresses().stream().map(address ->modelMapper.map(address,Address.class)).toList());
		customerJpaRepository.save(managedCustomer);
		var updateCustomerResponse = modelMapper.map(managedCustomer, UpdateCustomerResponse.class);
		updateCustomerResponse.setPhone(Base64.encode(managedCustomer.getPhoto()));
		return updateCustomerResponse;
	}

	@Override
	@Transactional
	public PatchCustomerResponse patchCustomer(String identity, Map<String, Object> request) {
		var managedCustomer = customerJpaRepository.findById(identity)
				.orElseThrow(() -> new CustomerNotFoundException());
		for (var entry : request.entrySet()) {
			var attribute = entry.getKey();
			var value = entry.getValue();
			System.err.println(entry);
			try {
				var field = Customer.class.getDeclaredField(attribute);
				field.setAccessible(true);
				field.set(managedCustomer, value);
				field.setAccessible(false);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				System.err.println(e.getMessage());
			}
		}
		return modelMapper.map(managedCustomer, PatchCustomerResponse.class);
	}

	@Override
	@Transactional
	public DeleteCustomerResponse removeCustomerByIdentity(String identity) {
		var managedCustomer = customerJpaRepository.findById(identity)
				.orElseThrow(() -> new CustomerNotFoundException());
		customerJpaRepository.delete(managedCustomer);
		return modelMapper.map(managedCustomer, DeleteCustomerResponse.class);
	}

}
