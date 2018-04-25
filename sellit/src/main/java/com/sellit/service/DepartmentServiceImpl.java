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
	public Optional<Department> findById(Long productId) {
		return departmentDao.findById(productId);
	}

	@Override
	public List<Department> findByDepartmentNameContaining(String departmentName) {
		return departmentDao.findByDepartmentNameContaining(departmentName);
	}

}
