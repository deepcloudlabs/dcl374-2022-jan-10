package com.example.crm.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.crm.application.CrmApplication;
import com.example.crm.application.event.CustomerAcquiredEvent;

@Service
public class CustomerWelcomeService {
	private CrmApplication crmApplication;
	
	public CustomerWelcomeService(CrmApplication crmApplication) {
		this.crmApplication = crmApplication;
	}

	@EventListener
	public void handleCustomerAcquiredEvent(CustomerAcquiredEvent event) {
		var identity = event.getIdentity();
		var customer = crmApplication.findCustomerByIdentity(identity);
		var email = customer.getEmail();
		System.err.println("Sending welcome message to the customer..."+email);
	}
}
