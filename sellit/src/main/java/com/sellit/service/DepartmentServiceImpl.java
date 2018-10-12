package com.sellit.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellit.SellitApplication;
import com.sellit.dao.DepartmentDao;
import com.sellit.persistence.Department;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public Department save(Department department) {

		if (department.getCreatedBy() == null) {
			department.setCreatedBy(SellitApplication.getApplicationContainer().getLoggedInEmployee());
			department.setCreatedDatetime(LocalDateTime.now());
		}

		department.setUpdatedBy(SellitApplication.getApplicationContainer().getLoggedInEmployee());
		department.setUpdatedDatetime(LocalDateTime.now());

		return departmentDao.save(department);
	}

	@Override
	public Department findById(Long productId) {
		Optional<Department> department = departmentDao.findById(productId);

		if (department.isPresent()) {
			return department.get();
		}

		return null;
	}

	@Override
	public List<Department> findByDepartmentNameContaining(String departmentName, String status) {
		return departmentDao.findByDepartmentNameContaining(departmentName, status);
	}

	@Override
	public List<Department> findByStatusOrderByDepartmentNameAsc(String status) {
		return departmentDao.findByStatusOrderByDepartmentNameAsc(status);
	}

}
