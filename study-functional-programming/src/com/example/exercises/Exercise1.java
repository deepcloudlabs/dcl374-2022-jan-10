package com.example.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

import com.example.domain.Department;
import com.example.domain.Employee;

public class Exercise1 {
	public static List<Employee> employees;
	static { // static initializer
		employees = new ArrayList<>();
		var jack = new Employee("1", "jack bauer", 100_000, "tr1", true, 1956);
		jack.addDepartments(Department.HR,Department.FINANCE,Department.IT);
		var kate = new Employee("2", "kate austen", 200_000, "tr2", false, 1986);
		kate.addDepartments(Department.HR,Department.IT);
		var james = new Employee("3", "james sawyer", 150_000, "tr3", true, 1978);
		james.addDepartments(Department.SALES,Department.FINANCE);
		var jin = new Employee("4", "jin kwon", 250_000, "tr4", false, 1987);
		jin.addDepartments(Department.IT);
		employees.add(jack);
		employees.add(kate);
		employees.add(james);
		employees.add(jin);
	}
	public static void main(String[] args) {
		// functions: 1) higher-order function: functions take function as a parameter
		//           Example: Stream API function
		//                    higher-order function: filter, map, flatMap, reduce 
		//                    these are not: limit(int), distinct()
		//            2) pure function: lambda expression, method reference
		//               FunctionalInterface is used to define pure functions!
		//               contains exactly one function! - SAM : single abstract method 
		
		// Stream API: Lazy Evaluation
		// 1) intermediate functions: filter, map, flatMap, limit, distinct, sorted, boxed, ...
		// 2) terminal functions    : reduce, max, min, sum, count, ...
		// actual computation starts with terminal method!
		
		Predicate<Employee> ifFullTime = employee -> employee.isFulltime(); 
		Predicate<Employee> isEmpPartTime = Exercise1::isPartTimeEmployee; 
		Elma                isEmpPartTime2= Exercise1::isPartTimeEmployee; 
		Predicate<Employee> ifPartTime = ifFullTime.negate();
		ToDoubleFunction<Employee> toSalary = employee -> employee.getSalary();
		var maxSalaryToPartTimeEmployees =
		employees.stream()              // Stream<Employee> , intermediate function
		         .filter(ifPartTime)    // Stream<Employee> , intermediate function
		         .mapToDouble(Employee::getSalary) // DoubleStream   , intermediate function
		         .max()
		         .getAsDouble();        //                    terminal function
		System.err.println(maxSalaryToPartTimeEmployees);
		
	}

	public static boolean isPartTimeEmployee(Employee emp) {
		return !emp.isFulltime();
	}
}

@FunctionalInterface
interface Elma {
	boolean armut(Employee employee);
}
