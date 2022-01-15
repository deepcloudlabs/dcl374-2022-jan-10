package com.example.crm.application.event;

import com.example.crm.dto.response.DetailedCustomerResponse;

public class CustomerReleasedEvent {
	private DetailedCustomerResponse customer;

	public CustomerReleasedEvent(DetailedCustomerResponse customer) {
		this.customer = customer;
	}

	public DetailedCustomerResponse getCustomer() {
		return customer;
	}

	public void setCustomer(DetailedCustomerResponse customer) {
		this.customer = customer;
	}

}
