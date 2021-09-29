package com.tcs.employeedata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "address")
	private String addressline;
	@Column(name = "pin")
	private int pincode;

	@JsonIgnore
	@OneToOne(mappedBy = "address")
	private Employee employee;
	
	/**
	 * @param addressline
	 * @param pincode
	 */
	public Address(String addressline, int pincode) {
		super();
		this.addressline = addressline;
		this.pincode = pincode;
	}

	/**
	 * @return the addressline
	 */
	public String getAddressline() {
		return addressline;
	}

	/**
	 * 
	 */
	public Address() {
		super();
	}

	/**
	 * @return the employee
	 */

	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param addressline the addressline to set
	 */
	public void setAddressline(String addressline) {
		this.addressline = addressline;
	}

	/**
	 * @return the pincode
	 */
	public int getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

}
