package com.example.crm.application;

import java.util.List;
import java.util.Map;

import com.example.crm.dto.request.AcquireCustomerRequest;
import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.AcquireCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.dto.response.DeleteCustomerResponse;
import com.example.crm.dto.response.DetailedCustomerResponse;
import com.example.crm.dto.response.PatchCustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;

public interface CrmApplication {

	DetailedCustomerResponse findCustomerByIdentity(String identity);

	List<CustomerResponse> findAllByPage(int pageNo, int pageSize);

	AcquireCustomerResponse addCustomer(AcquireCustomerRequest customer);

	UpdateCustomerResponse updateCustomer(String identity, UpdateCustomerRequest customer);

	PatchCustomerResponse patchCustomer(String identity, Map<String, Object> changes);

	DeleteCustomerResponse removeCustomerByIdentity(String identity);

}
