package com.example.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.domain.Department;
import com.example.domain.Employee;

public class Exercise5 {
	public static List<Employee> employees;
	static { // static initializer
		employees = new ArrayList<>();
		var jack = new Employee("1", "jack bauer", 100_000, "tr1", true, 1956);
		jack.addDepartments(Department.HR, Department.FINANCE, Department.IT);
		var kate = new Employee("2", "kate austen", 200_000, "tr2", false, 1986);
		kate.addDepartments(Department.HR, Department.IT);
		var james = new Employee("3", "james sawyer", 150_000, "tr3", true, 1978);
		james.addDepartments(Department.SALES, Department.FINANCE);
		var jin = new Employee("4", "jin kwon", 250_000, "tr4", false, 1987);
		jin.addDepartments(Department.IT);
		employees.add(jack);
		employees.add(kate);
		employees.add(james);
		employees.add(jin);
	}

	public static void main(String[] args) {
		// Find the total salaries of Full-time and Part-time employees
		var totalSalaries =
		employees.stream()
		         .collect(Collectors.groupingBy(Employee::isFulltime,Collectors.summingDouble(Employee::getSalary)));
		totalSalaries.forEach((isFullTime,totalSalary) -> System.err.printf("%s: %f\n",isFullTime?"Full-time":"Part-time",totalSalary));		
	}

}
