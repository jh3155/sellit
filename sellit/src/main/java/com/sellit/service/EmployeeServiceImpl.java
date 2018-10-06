package com.sellit.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellit.dao.EmployeeDao;
import com.sellit.persistence.Department;
import com.sellit.persistence.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee findById(Long employeeId) {
		Optional<Employee> employee = employeeDao.findById(employeeId);

		if (employee.isPresent()) {
			return employee.get();
		}

		return null;
	}

	@Override
	public Employee save(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public List<Employee> findByEmployeeNameContaining(final String employeeName, String status) {
		return employeeDao.findByEmployeeNameContaining(employeeName, status);
	}

}
