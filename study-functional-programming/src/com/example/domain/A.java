package com.example.domain;

import java.util.concurrent.ThreadLocalRandom;

public class A {
	private int x = 42; // instance -> new 
	private static int y = 42; // global -> one copy
	
	{ // new -> every time you create object
		System.err.println("What is this?");
		x = 42;
	}

	static { // Class Loader : just once
		y = ThreadLocalRandom.current().nextInt(10, 100);
		System.err.println("static initializer: "+y);
	}
	
	public A() {
		System.err.println("A::A()");
		x = 42;
	}

	public A(int x) {
		System.err.println("A::A(int)");
		this.x = x;
	}
	public A(int u,int v) {
		System.err.println("A::A(int,int)");
		this.x = u * u + v;
	}
	
}
