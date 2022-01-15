package com.example.service;

import java.util.concurrent.TimeUnit;

public class BusinessService {
	public int getBusinessData() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
		}
		return 42;
	}
}
