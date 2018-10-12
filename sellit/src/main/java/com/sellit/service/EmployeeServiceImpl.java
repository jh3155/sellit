package com.sellit.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellit.SellitApplication;
import com.sellit.constants.StatusConstants;
import com.sellit.dao.EmployeeDao;
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

		if (employee.getCreatedBy() == null) {
			employee.setCreatedBy(SellitApplication.getApplicationContainer().getLoggedInEmployee());
			employee.setCreatedDatetime(LocalDateTime.now());
		}

		employee.setUpdatedBy(SellitApplication.getApplicationContainer().getLoggedInEmployee());
		employee.setUpdatedDatetime(LocalDateTime.now());

		return employeeDao.save(employee);
	}

	@Override
	public List<Employee> findByEmployeeNameContaining(final String employeeName, String status) {
		return employeeDao.findByEmployeeNameContaining(employeeName, status);
	}

	@Override
	public Employee findByPin(final String pin) {
		List<Employee> employees = employeeDao.findByPinAndStatus(pin, StatusConstants.ACTIVE);

		Optional<Employee> employee = employees.stream().findFirst();

		if (employee.isPresent()) {
			return employee.get();
		}

		return null;
	}

}
