package com.sellit.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellit.dao.DepartmentDao;
import com.sellit.persistence.Department;
import com.sellit.persistence.Department;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public Department save(Department department) {
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
