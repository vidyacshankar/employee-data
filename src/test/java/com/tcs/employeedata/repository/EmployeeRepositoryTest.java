package com.tcs.employeedata.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.tcs.employeedata.entity.Address;
import com.tcs.employeedata.entity.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {

@Autowired
private TestEntityManager entityManager;

@Autowired
private EmployeeRepository repository;

@Test
public void testSaveEmployee() {
Employee emp = repository.save(new Employee("Kate", "TCS", new Address("Bangalore", 560057)));
assertNotNull(emp.getId());
}

@Test
public void testFindAllEmployee() {
entityManager.persist(new Employee("Kate", "TCS", new Address("Bangalore", 560057)));
entityManager.persist(new Employee("John", "TCS", new Address("Bangalore", 560057)));
entityManager.persist(new Employee("Jack", "TCS", new Address("Bangalore", 560057)));

List<Employee> employeeList = repository.findAll();

assertEquals(employeeList.size(), 3);
assertEquals(employeeList.get(0).getName(), "Kate");
}

@Test
public void testDeleteAllEmployee() {
entityManager.persist(new Employee("Kate", "TCS", new Address("Bangalore", 560057)));
entityManager.persist(new Employee("John", "TCS", new Address("Bangalore", 560057)));
entityManager.persist(new Employee("Jack", "TCS", new Address("Bangalore", 560057)));

List<Employee> employeeList = repository.findAll();
assertEquals(employeeList.size(), 3);

repository.deleteAll();
   Assertions.assertThat(repository.findAll()).isEmpty();
}

}
