package com.example.hr.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.application.HrApplication;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.request.UpdateEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.validation.TcKimlikNo;

@RestController
@RequestScope
@RequestMapping("employees")
@CrossOrigin
@Validated
public class HrRestController {
	private HrApplication hrApplication;

	public HrRestController(HrApplication hrApplication) {
		this.hrApplication = hrApplication;
	}

	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request) {
		return hrApplication.hireEmployee(request);
	}
	
	@PutMapping("{identity}")
	public EmployeeResponse updateEmployee(@PathVariable @TcKimlikNo String identity,
			@RequestBody @Validated UpdateEmployeeRequest request) {
		return hrApplication.updateEmployee(identity, request);
	}

	@DeleteMapping("{identity}")
	public FireEmployeeResponse fireEmployee(@PathVariable @TcKimlikNo String identity) {
		return hrApplication.fireEmployee(identity);
	}

	@GetMapping
	public List<EmployeeResponse> getEmployees(@RequestParam @Min(0) int page, @RequestParam @Max(25) int size) {
		return hrApplication.findEmployees(page, size);
	}

	@GetMapping("{identity}")
	public EmployeeResponse getEmployeeByIdentity(@PathVariable @TcKimlikNo String identity) {
		return hrApplication.findEmployeeByIdentity(identity);
	}

}