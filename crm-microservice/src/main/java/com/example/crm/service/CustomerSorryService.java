package com.example.crm.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.crm.application.event.CustomerReleasedEvent;

@Service
public class CustomerSorryService {

	@EventListener
	public void handleCustomerAcquiredEvent(CustomerReleasedEvent event) {
		var email = event.getCustomer().getEmail();
		System.err.println("Sending sorry message to the customer..." + email);
	}
}
