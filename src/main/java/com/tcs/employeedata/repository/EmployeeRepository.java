package com.tcs.employeedata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.employeedata.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
