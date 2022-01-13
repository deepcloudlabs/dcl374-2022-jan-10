package com.example.crm.application.business;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.application.CrmApplication;
import com.example.crm.application.business.exception.CustomerAlreadyExistException;
import com.example.crm.application.business.exception.CustomerNotFoundException;
import com.example.crm.document.CustomerDocument;
import com.example.crm.dto.request.AcquireCustomerRequest;
import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.AcquireCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.dto.response.DeleteCustomerResponse;
import com.example.crm.dto.response.DetailedCustomerResponse;
import com.example.crm.dto.response.PatchCustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;
import com.example.crm.repository.CustomerMongoRepository;

@Service
@ConditionalOnProperty(name="crm.persistence", havingValue = "mongodb")
public class NoSqlCrmApplication implements CrmApplication {
	private CustomerMongoRepository customerMongoRepository;
	private ModelMapper modelMapper;
	
	public NoSqlCrmApplication(CustomerMongoRepository customerMongoRepository, ModelMapper modelMapper) {
		this.customerMongoRepository = customerMongoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public DetailedCustomerResponse findCustomerByIdentity(String identity) {
		var customer = customerMongoRepository.findById(identity)
				.orElseThrow( () -> new CustomerNotFoundException());
		return modelMapper.map(customer, DetailedCustomerResponse.class);
	}

	@Override
	public List<CustomerResponse> findAllByPage(int pageNo, int pageSize) {
		var page = PageRequest.of(pageNo, pageSize);
		return customerMongoRepository.findAll(page)
				                      .stream()
				                      .map(cust -> modelMapper.map(cust, CustomerResponse.class))
				                      .toList();
	}

	@Override
	public AcquireCustomerResponse addCustomer(AcquireCustomerRequest request) {
		String identity = request.getIdentity();
		if (customerMongoRepository.existsById(identity )) {
			throw new CustomerAlreadyExistException();
		}
		var customer = modelMapper.map(request, CustomerDocument.class);
		return modelMapper.map(customerMongoRepository.save(customer),
				AcquireCustomerResponse.class);
	}

	@Override
	public UpdateCustomerResponse updateCustomer(String identity, UpdateCustomerRequest customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatchCustomerResponse patchCustomer(String identity, Map<String, Object> changes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteCustomerResponse removeCustomerByIdentity(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

}
