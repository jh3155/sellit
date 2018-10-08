package com.sellit.service;

import java.util.List;

import com.sellit.persistence.Employee;

public interface EmployeeService {

	Employee save(Employee employee);

	Employee findById(Long employeeId);

	List<Employee> findByEmployeeNameContaining(String employeeName, String status);

	Employee findByPin(String pin);

}