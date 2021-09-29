package com.tcs.employeedata.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.tcs.employeedata.entity.Address;
import com.tcs.employeedata.entity.Employee;
import com.tcs.employeedata.entity.Project;
import com.tcs.employeedata.exception.EmployeeNotFoundException;
import com.tcs.employeedata.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository repository) {
		this.employeeRepository = repository;
		
	}

	public Employee createEmployee(Employee employee) {
		employee.getProjects().forEach(project -> project.setEmployee(employee));
		return employeeRepository.save(employee);
	}

	public List<Employee> getEmployeeList() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeebyId(long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with the id " + id));
	}

	public String deleteEmployeebyId(long id) {
		employeeRepository.deleteById(id);
		return "success";
	}

	public Employee updateEmployee(Employee employee, Long id) {
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
		existingEmployee.setName(employee.getName());
		existingEmployee.setCompany(employee.getCompany());
		return employeeRepository.save(existingEmployee);
	}

	public List<Employee> getEmployeeByCompany(String company) {
		return employeeRepository.findAll().stream().filter(employee -> employee.getCompany().equalsIgnoreCase(company))
				.collect(Collectors.toList());
	}

	public void createAddressById(long id, Address address) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			Employee e1 = employee.get();
			e1.setAddress(address);
			address.setEmployee(e1);
			employeeRepository.save(e1);
		}
	}

	public void createProjectById(long id, Project project) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			Employee e1 = employee.get();
			project.setEmployee(e1);
			Set<Project> projects = e1.getProjects();
			projects.add(project);
			e1.setProjects(projects);
			employeeRepository.save(e1);
		}
	}

}
