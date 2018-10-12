package com.sellit.service;

import java.util.List;

import com.sellit.persistence.Department;

public interface DepartmentService {

	Department save(Department department);

	Department findById(Long productId);

	List<Department> findByDepartmentNameContaining(String departmentName, String status);

	List<Department> findByStatusOrderByDepartmentNameAsc(String status);

}