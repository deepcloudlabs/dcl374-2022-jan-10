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
import com.example.crm.entity.Customer;
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
	public Customer getCustomerByIdentity(
			@PathVariable @TcKimlikNo String identity) {
		return crmApplication.findCustomerByIdentity(identity);
	}
	
	@GetMapping
	public List<Customer> getCustomersByPage(
			@RequestParam(name = "page") @Min(0) int pageNo,
			@RequestParam(name = "size") @Max(25) int pageSize
			){
		return crmApplication.findAllByPage(pageNo,pageSize);
	}
	
	@PostMapping
	public Customer acquireCustomer(@RequestBody @Validated Customer customer) {
		return crmApplication.addCustomer(customer);
	}
	
	@PutMapping("{identity}")
	public Customer updateCustomer(
			@PathVariable @Validated @TcKimlikNo String identity,
			@RequestBody @Validated Customer customer) {
		return crmApplication.updateCustomer(identity,customer);
	}
	
	@PatchMapping("{identity}")
	public Customer patchCustomer(
			@PathVariable @Validated @TcKimlikNo String identity,
			@RequestBody Map<String,Object> changes
			) {
		return crmApplication.patchCustomer(identity, changes);
	}
	
	@DeleteMapping("{identity}")
	public Customer releaseCustomerByIdentity(
			@PathVariable @TcKimlikNo String identity) {
		return crmApplication.removeCustomerByIdentity(identity);
	}
}
