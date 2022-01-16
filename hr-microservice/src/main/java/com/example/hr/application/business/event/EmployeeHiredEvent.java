package com.example.hr.application.business.event;

import java.util.UUID;

import com.example.hr.dto.response.EmployeeResponse;

public class EmployeeHiredEvent extends EmployeeEvent {

	private final EmployeeResponse employee;

	public EmployeeHiredEvent(EmployeeResponse employee) {
		super(UUID.randomUUID().toString());
		this.employee = employee;
	}

	public EmployeeResponse getEmployee() {
		return employee;
	}

}
