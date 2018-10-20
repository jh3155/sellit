package com.sellit.persistence;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseTable {

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
