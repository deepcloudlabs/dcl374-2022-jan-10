package com.example.crm.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@KafkaListener(topics = "crm-events", groupId = "crm-consumer")
	public void listenCustomerAcquiredEvent(String message) {
		System.err.println("New message has arrived: "+message);
	}
}
