package com.example.crm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.application.CrmApplication;
import com.example.crm.dto.request.AcquireCustomerRequest;
import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.AcquireCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.dto.response.DeleteCustomerResponse;
import com.example.crm.dto.response.DetailedCustomerResponse;
import com.example.crm.dto.response.PatchCustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;
import com.example.validation.TcKimlikNo;

@RestController
@RequestScope
@RequestMapping("customers")
@CrossOrigin
@Validated
public class CrmRestController {
	private CrmApplication crmApplication;

	public CrmRestController(CrmApplication crmApplication) {
		this.crmApplication = crmApplication;
		System.err.println(crmApplication.getClass().getName());
	}

	// GET /customers/11111111110
	@GetMapping("{identity}")
	public DetailedCustomerResponse getCustomerByIdentity(@PathVariable @TcKimlikNo String identity) {
		return crmApplication.findCustomerByIdentity(identity);
	}

	@GetMapping
	public List<CustomerResponse> getCustomersByPage(@RequestParam(name = "page") @Min(0) int pageNo,
			@RequestParam(name = "size") @Max(25) int pageSize) {
		return crmApplication.findAllByPage(pageNo, pageSize);
	}

	@PostMapping
	public AcquireCustomerResponse acquireCustomer(@RequestBody @Validated AcquireCustomerRequest request) {
		return crmApplication.addCustomer(request);
	}

	@PutMapping("{identity}")
	public UpdateCustomerResponse updateCustomer(@PathVariable @Validated @TcKimlikNo String identity,
			@RequestBody @Validated UpdateCustomerRequest request) {
		return crmApplication.updateCustomer(identity, request);
	}

	@PatchMapping("{identity}")
	public PatchCustomerResponse patchCustomer(@PathVariable @Validated @TcKimlikNo String identity,
			@RequestBody Map<String, Object> changes) {
		return crmApplication.patchCustomer(identity, changes);
	}

	@DeleteMapping("{identity}")
	public DeleteCustomerResponse releaseCustomerByIdentity(@PathVariable @TcKimlikNo String identity) {
		return crmApplication.removeCustomerByIdentity(identity);
	}
}
