package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncBusinessService {
	ExecutorService threadPool = Executors.newFixedThreadPool(100);
	public CompletableFuture<Integer> getBusinessData() {
		return CompletableFuture.supplyAsync( () -> {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
			}
			return 42;			
		}, threadPool);
	}
}
