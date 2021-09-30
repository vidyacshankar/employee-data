package com.tcs.employeedata.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.employeedata.entity.Address;
import com.tcs.employeedata.entity.Employee;
import com.tcs.employeedata.entity.Project;

import com.tcs.employeedata.service.EmployeeService;

@RestController
@RequestMapping("/employees/")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired

	public EmployeeController(EmployeeService Service) {
		this.employeeService = Service;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<Employee> getEmployeeList() {
		return employeeService.getEmployeeList();
	}

	@GetMapping("{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Employee getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeebyId(id);

	}

	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteEmployeeById(@PathVariable Long id) {
		return employeeService.deleteEmployeebyId(id);
	}

	@PutMapping("{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		return employeeService.updateEmployee(employee, id);
	}

	@GetMapping("/byCompany")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Employee> getEmployeeByCompany(@RequestParam String company) {
		return employeeService.getEmployeeByCompany(company);
	}

	@PostMapping("{id}/address")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createAddressById(@PathVariable Long id, @RequestBody Address address) {
		employeeService.createAddressById(id, address);
	}

	@PostMapping("{id}/project")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createProjectsById(@PathVariable Long id, @RequestBody Project project) {
		employeeService.createProjectById(id, project);
	}
}
