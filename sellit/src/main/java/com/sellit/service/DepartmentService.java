package com.sellit.service;

import java.util.List;
import java.util.Optional;

import com.sellit.persistence.Department;
import com.sellit.persistence.Department;

public interface DepartmentService {

	Department save(Department department);

	Optional<Department> findById(Long productId);

	List<Department> findByDepartmentNameContaining(String departmentName);

	List<Department> findByStatusOrderByDepartmentNameAsc(String status);

}