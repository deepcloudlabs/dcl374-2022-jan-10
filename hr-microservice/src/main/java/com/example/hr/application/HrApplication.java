package com.example.hr.application;

import java.util.List;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.request.UpdateEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

public interface HrApplication {

	HireEmployeeResponse hireEmployee(HireEmployeeRequest request);

	EmployeeResponse updateEmployee(String identity, UpdateEmployeeRequest request);

	FireEmployeeResponse fireEmployee(String identity);

	List<EmployeeResponse> findEmployees(int page,int size);

	EmployeeResponse findEmployeeByIdentity(String identity);

}


