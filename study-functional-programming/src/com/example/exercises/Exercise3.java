package com.example.exercises;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.Department;
import com.example.domain.Employee;

public class Exercise3 {
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
		var totalSalaryOfITDepartment =
		employees.stream()
		         .filter(employee -> employee.getDepartments().contains(Department.IT))
		         .mapToDouble(Employee::getSalary)
		         .sum();
		System.err.println(totalSalaryOfITDepartment);
	}

}
