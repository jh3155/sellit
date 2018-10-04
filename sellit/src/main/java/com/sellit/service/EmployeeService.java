package com.sellit.service;

import java.util.List;

import com.sellit.persistence.Employee;

public interface EmployeeService {

	List<Employee> findByEmployeeNameContaining(String employeeName, String status);

}