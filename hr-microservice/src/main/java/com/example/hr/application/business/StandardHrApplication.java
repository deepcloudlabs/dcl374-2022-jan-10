package com.example.hr.application.business;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.event.EmployeeFiredEvent;
import com.example.hr.application.business.event.EmployeeHiredEvent;
import com.example.hr.application.business.exception.EmployeeAlreadyExistException;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.document.EmployeeDocument;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.request.UpdateEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.repository.EmployeeMongoRepository;

@Service
public class StandardHrApplication implements HrApplication {
	private EmployeeMongoRepository employeeMongoRepository;
	private ModelMapper modelMapper;
	private ApplicationEventPublisher eventPublisher;

	public StandardHrApplication(EmployeeMongoRepository employeeMongoRepository, ModelMapper modelMapper,
			ApplicationEventPublisher eventPublisher) {
		this.employeeMongoRepository = employeeMongoRepository;
		this.modelMapper = modelMapper;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var identity = request.getIdentity();
		if( employeeMongoRepository.existsById(identity))
			throw new EmployeeAlreadyExistException();
		var employee = modelMapper.map(request, EmployeeDocument.class);
		employee = employeeMongoRepository.insert(employee);
		var employeeHiredEvent = new EmployeeHiredEvent(modelMapper.map(employee,EmployeeResponse.class));
		eventPublisher.publishEvent(employeeHiredEvent);
		return modelMapper.map(employee, HireEmployeeResponse.class);
	}

	@Override
	public EmployeeResponse updateEmployee(String identity, UpdateEmployeeRequest request) {
		var employee = employeeMongoRepository.findById(identity).orElseThrow(() -> new EmployeeNotFoundException());
		modelMapper.map(request, employee);
		employee.setIdentity(identity);
		return modelMapper.map(employeeMongoRepository.save(employee), EmployeeResponse.class);
	}

	@Override
	public FireEmployeeResponse fireEmployee(String identity) {
		var employee = employeeMongoRepository.findById(identity).orElseThrow(() -> new EmployeeNotFoundException());
		employeeMongoRepository.delete(employee);
		var fireEmployeeResponse = modelMapper.map(employee, FireEmployeeResponse.class);
		var employeeFiredEvent = new EmployeeFiredEvent(modelMapper.map(employee,EmployeeResponse.class));
		eventPublisher.publishEvent(employeeFiredEvent);
		return fireEmployeeResponse;
	}

	@Override
	public List<EmployeeResponse> findEmployees(int page,int size) {
		var pageRequest = PageRequest.of(page, size);
		return employeeMongoRepository.findAll(pageRequest).stream().map(emp -> modelMapper.map(emp, EmployeeResponse.class))
				.toList();		
	}

	@Override
	public EmployeeResponse findEmployeeByIdentity(String identity) {
		var employee = employeeMongoRepository.findById(identity).orElseThrow(() -> new EmployeeNotFoundException());
		employeeMongoRepository.delete(employee);
		var employeeFiredEvent = new EmployeeFiredEvent(modelMapper.map(employee, EmployeeResponse.class));
		eventPublisher.publishEvent(employeeFiredEvent);
		return modelMapper.map(employee, EmployeeResponse.class);
	}

}
