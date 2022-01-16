package com.example.exercises;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.domain.Department;
import com.example.domain.Employee;

public class Exercise7 {
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
		// Find the employee numbers in age groups [20-30), [30-40), [40-50), etc.
		var employeeAgeHistogram = 
		employees.stream()
		         .map(AgeRange::makeAgeRange)
		         .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		employeeAgeHistogram.forEach((ageRange,count)->System.err.println(ageRange.label()+": "+count));
	}

}

record AgeRange(int start, int range, String label) {

	private static final Map<Integer, AgeRange> cache = new HashMap<>();

	public static AgeRange makeAgeRange(Employee employee) {
		var age = LocalDate.now().getYear() - employee.getBirthYear();
		var start = age - age % 10;
		var ageRange = cache.get(start);
		if (Objects.isNull(ageRange)) {
			ageRange = new AgeRange(start, 10, String.format("[%d,%d)", start , start + 10));
			cache.put(start, ageRange);
		}
		return ageRange;
	}

}
