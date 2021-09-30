package com.tcs.employeedata.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.employeedata.entity.Address;
import com.tcs.employeedata.entity.Employee;
import com.tcs.employeedata.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private EmployeeRepository repository;
	
	private long employeeId;
	private Employee employee;
	
	@BeforeEach
	void init() throws Exception {
		employee = new Employee("Kate", "TCS", new Address("Bangalore", 560057));

		employeeId = mapper.readValue(mockMvc
				.perform(post("/employees/").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(employee)))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), Employee.class)
				.getId();
	}

	@Test
	public void testCreateEmployee() throws Exception {
		assertThat(repository.findById(employeeId).get().getName(), equalTo("Kate"));
	}

	@Test
	public void testGetEmployeeList() throws Exception {
		mockMvc.perform(get("/employees/")).andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$", hasSize((int) repository.count())));
	}

	@Test
	@DisplayName("junit to test getEmployeeById")
	public void testGetEmployeebyId() throws Exception {
	
		Employee employee2 = mapper.readValue(mockMvc
				.perform(get("/employees/" + employeeId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Employee.class);
		assertEquals(employee.getName(), employee2.getName());
	}
	
	@Test
	@DisplayName("junit to test deleteEmployeeById")
	public void testDeleteEmployeeById() throws Exception {
		Employee employee = new Employee("Kate", "TCS", new Address("Bangalore", 560057));
		Long employeeId = mapper.readValue(mockMvc.perform(post("/employees/").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(employee)))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), Employee.class)
				.getId();

		Employee employee2 = mapper.readValue(mockMvc
				.perform(get("/employees/" + employeeId).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(employee)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Employee.class);
		assertEquals(employee.getName(), employee2.getName());
	}

}
