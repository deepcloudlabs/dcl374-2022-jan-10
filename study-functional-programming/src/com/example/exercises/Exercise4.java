package com.example.exercises;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.domain.Department;
import com.example.domain.Employee;

public class Exercise4 {
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
//		[(jack,IT), (jack,FINANCE), (jack,HR)]   Stream<List<Pair>>
//		[(kate,HR), (kate,IT)]
// 		[(james, SALES), (james, FINANCE)]
//      [(jin,IT)]		

//      (jack,IT)                                Stream<Pair> 
//      (jack,FINANCE)
//      (jack,HR)
//      (kate,HR)
//      (kate,IT)
//      (james,SALES)
//      (james,FINANCE)
//      (jin,IT)
		
		
		Function<DepartmentSalaryPair,Department> department = DepartmentSalaryPair::department;
		Collector<DepartmentSalaryPair, ?, Double> summingSalary = Collectors.summingDouble(DepartmentSalaryPair::salary);
		var groupByDepartmentGetTotalSalary = 
		employees.stream()
		         .flatMap(employee -> {
					Function<Department,DepartmentSalaryPair> toDepartmentSalaryPair = dept -> new DepartmentSalaryPair(dept,employee.getSalary());
					return employee.getDepartments().stream().map(toDepartmentSalaryPair).toList().stream();
				})		         
		         .collect(groupingBy(department,summingSalary)); // terminal
		groupByDepartmentGetTotalSalary.forEach((dept,total)->System.err.println(dept+": "+total));
	}

}

record DepartmentSalaryPair(Department department, double salary) {}