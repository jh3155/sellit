package com.sellit.persistence;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sellit.constants.EmployeeConstants;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;

	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "PIN")
	private String pin;

	@Column(name = "EMPLOYEE_LEVEL")
	private String employeeLevel = EmployeeConstants.LEVEL_STAFF;

	@Column(name = "STATUS")
	private String status;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "CREATED_BY", referencedColumnName = "EMPLOYEE_ID")
	private Employee createdBy;

	@Column(name = "CREATED_DTM")
	private LocalDateTime createdDatetime;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "UPDATED_BY", referencedColumnName = "EMPLOYEE_ID")
	private Employee updatedBy;

	@Column(name = "UPDATED_DTM")
	private LocalDateTime updatedDatetime;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getEmployeeLevel() {
		return employeeLevel;
	}

	public void setEmployeeLevel(String employeeLevel) {
		this.employeeLevel = employeeLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Employee getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Employee updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(LocalDateTime updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

}
