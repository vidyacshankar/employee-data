package com.tcs.employeedata.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tcs.employeedata.validation.Name;





@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull(message = "name should not be null")
	@Size(min=2, message = "name should be of atleast 2 characters")
	@Column(name = "employeeName", nullable = false)
	@Name(message = "name should not be numeric")
	private String name;
	@NotNull(message = "company name should not be null")
	@Size(min = 2, message = "company name should be of atleast 2 characters")
	@Column(name = "companyName", nullable = false)
	private String company;

	@OneToOne(targetEntity = Address.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	private Set<Project> projects = new HashSet<Project>();

	/**
	 * @param name
	 * @param company
	 * @param address
	 */
	public Employee( String name, String company, Address address) {
		super();
		this.name = name;
		this.company = company;
		this.address = address;
	}

	/**
	 * @param name
	 * @param company
	 * @param projects
	 */
	public Employee(String name, String company, Set<Project> projects) {
		super();
		this.name = name;
		this.company = company;
		this.projects = projects;
	}

	/**
	 * @param name
	 * @param company
	 * @param address
	 * @param projects
	 */
	public Employee(String name, String company, Address address, Set<Project> projects) {
		super();
		this.name = name;
		this.company = company;
		this.address = address;
		this.projects = projects;
	}

	/**
	 * 
	 */
	public Employee() {
		super();
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	
}
