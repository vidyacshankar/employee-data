package com.tcs.employeedata.exception;

import java.util.Date;
import java.util.List;

//import javax.xml.bind.annotation.XmlRootElement;
//@XmlRootElement(name = "errorDetail")
public class ErrorDetail {
	
	private Date timestamp;
	private String Message;
	private List<String> details;
	
	/**
	 * @param timestamp
	 * @param message
	 * @param details
	 */
	public ErrorDetail(Date timestamp, String message, List<String> details) {
		super();
		this.timestamp = timestamp;
		this.Message = message;
		this.details = details;
	}
	
	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return Message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		Message = message;
	}
	/**
	 * @return the details
	 */
	public List<String> getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(List<String> details) {
		this.details = details;
	}
	
}
	
	
