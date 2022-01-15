package com.example.application;

import com.example.service.BusinessService;

public class BusinessApp {

	public static void main(String[] args) {
		// Blocking -> Sync
		System.err.println("Application is running...");
		BusinessService businessService = new BusinessService();
		for (var i=0;i<1_000;++i)
		    System.err.println(businessService.getBusinessData()); 
		System.err.println("Application is done.");
	}

}
