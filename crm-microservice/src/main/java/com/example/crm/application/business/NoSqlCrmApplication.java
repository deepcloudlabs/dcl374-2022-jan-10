package com.example.crm.application.business;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;

import com.example.crm.application.CrmApplication;
import com.example.crm.dto.request.AcquireCustomerRequest;
import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.AcquireCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.dto.response.DeleteCustomerResponse;
import com.example.crm.dto.response.DetailedCustomerResponse;
import com.example.crm.dto.response.PatchCustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;
import com.example.crm.repository.CustomerJpaRepository;

@Named // CDI
@Scope("request") // Spring
@RequestScoped // CDI
//@Service
@ConditionalOnProperty(name="crm.persistence", havingValue = "mongodb")
public class NoSqlCrmApplication implements CrmApplication {

	// @Autowired
	@Inject
	private CustomerJpaRepository customerJpaRepository;
	
	@Override
	public DetailedCustomerResponse findCustomerByIdentity(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerResponse> findAllByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AcquireCustomerResponse addCustomer(AcquireCustomerRequest customer) {
		// TODO Auto-generated method stub
		return null;
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
