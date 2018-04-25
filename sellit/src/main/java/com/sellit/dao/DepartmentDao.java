package com.sellit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellit.persistence.Department;

public interface DepartmentDao extends JpaRepository<Department, Long> {

	List<Department> findByDepartmentNameContaining(String departmentName);

}
