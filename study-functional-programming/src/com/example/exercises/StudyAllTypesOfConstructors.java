package com.example.exercises;

import com.example.domain.A;

public class StudyAllTypesOfConstructors {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.example.domain.A"); // Class loader -> JVM
//		A a1 = new A();    // {} -> A()
		A a2 = new A(42); // {} -> A(int)
//		A a3 = new A(3,5); // {} -> A(int,int)

	}

}
