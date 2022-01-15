package com.example.application;

import java.util.concurrent.TimeUnit;

import com.example.service.AsyncBusinessService;
import com.example.service.BusinessService;

public class AsyncBusinessApp {

	public static void main(String[] args) throws InterruptedException {
		// Non-Blocking -> Async
		System.err.println("Application is running...");
		AsyncBusinessService asyncBusinessService = new AsyncBusinessService();
		
		for (var i = 0;i<1_000;++i)
			asyncBusinessService.getBusinessData()
			                    .thenAccept(System.err::println); // callback function
		TimeUnit.SECONDS.sleep(30); // 10
		System.err.println("Application is done.");
	}

}
