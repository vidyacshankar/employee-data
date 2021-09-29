package com.tcs.employeedata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tcs.employeedata.entity.Address;
import com.tcs.employeedata.entity.Employee;
import com.tcs.employeedata.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeService employeeService;

	@Mock
	EmployeeRepository employeeRepository;

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

		Mockito.when(employeeRepository.findAll()).thenReturn(list);

		List<Employee> empList = employeeService.getEmployeeList();

		assertEquals(3, empList.size());
		verify(employeeRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("junit to test createEmployee")
	public void testCreateEmployee() {
		Employee employee = new Employee("John", "TCS", new Address("Bangalore", 560057));
		Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

		employeeService.createEmployee(employee);
		verify(employeeRepository, times(1)).save(employee);
	}

	@Test
	@DisplayName("junit to test getEmployeeById")
	public void testGetEmployeebyId() {
		Employee empOne = new Employee("John", "TCS", new Address("Bangalore", 560057));
		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(empOne));

		Employee employee = employeeService.getEmployeebyId(1L);
		assertEquals(employee.getName(), "John");
		verify(employeeRepository, times(1)).findById(1L);
	}

	@Test
	@DisplayName("Junit to test updateEmployee")
	public void testUpdateEmployee() {
		Employee employee = new Employee("John", "TCS", new Address("Bangalore", 560057));
		Employee updatedEmployee = new Employee("Alex", "TCS", new Address("Bangalore", 560057));

		Mockito.when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
		Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

		Employee employeeUpdated = employeeService.updateEmployee(updatedEmployee, 1L);
		assertEquals(updatedEmployee, employeeUpdated);
		verify(employeeRepository, times(1)).save(employee);
	}

	@Test
	@DisplayName("Junit to test deleteById")
	public void testDeletebyId() {
		String employee = employeeService.deleteEmployeebyId(1L);
		assertEquals("success", employee);
		verify(employeeRepository, times(1)).deleteById(1L);

	}

}
