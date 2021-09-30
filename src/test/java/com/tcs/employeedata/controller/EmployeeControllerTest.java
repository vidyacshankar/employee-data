package com.tcs.employeedata.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tcs.employeedata.entity.Address;
import com.tcs.employeedata.entity.Employee;
import com.tcs.employeedata.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeService employeeService;

	@Test
	@DisplayName("junit to test getEmployeeList")
	public void testGetEmployeeList() {

		List<Employee> list = new ArrayList<Employee>();
		Employee empOne = new Employee("John", "TCS", new Address("Bangalore", 560057));
		Employee empTwo = new Employee("Kate", "Accenture", new Address("Bangalore", 560058));
		Employee empThree = new Employee("Chris", "CTS", new Address("Bangalore", 560059));

		list.add(empOne);
		list.add(empTwo);
		list.add(empThree);

		Mockito.when(employeeService.getEmployeeList()).thenReturn(list);

		List<Employee> empList = employeeController.getEmployeeList();

		assertEquals(3, empList.size());
		verify(employeeService, times(1)).getEmployeeList();
	}

	@Test
	@DisplayName("junit to test createEmployee")
	public void testCreateEmployee() {
		Employee employee = new Employee("John", "TCS", new Address("Bangalore", 560057));
		Mockito.when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

		employeeController.createEmployee(employee);
		verify(employeeService, times(1)).createEmployee(employee);
	}

	@Test
	@DisplayName("junit to test getEmployeeById")
	public void testGetEmployeebyId() {
		Employee empOne = new Employee("John", "TCS", new Address("Bangalore", 560057));
		Mockito.when(employeeService.getEmployeebyId(1L)).thenReturn(empOne);

		Employee employee = employeeController.getEmployeeById(1L);
		assertEquals(employee.getName(), "John");
		verify(employeeService, times(1)).getEmployeebyId(1l);
	}

	@Test
	@DisplayName("Junit to test updateEmployee")
	public void testUpdateEmployee() {
		Employee updatedEmployee = new Employee("Alex", "TCS", new Address("Bangalore", 560057));

		Mockito.when(employeeService.updateEmployee(any(Employee.class), any(Long.class))).thenReturn(updatedEmployee);

		Employee employeeUpdated = employeeController.updateEmployee(updatedEmployee, 1L);
		assertEquals(updatedEmployee.getName(), employeeUpdated.getName());
	}

	@Test
	@DisplayName("Junit to test deleteById")
	public void testDeletebyId() {
		employeeController.deleteEmployeeById(1L);
		verify(employeeService, times(1)).deleteEmployeebyId(1L);

	}
}
