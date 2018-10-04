package com.sellit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sellit.persistence.Department;

public interface DepartmentDao extends JpaRepository<Department, Long> {

	// add nativeQuery = true if actual column names are needed to be used
	@Query(value = "SELECT d FROM Department d WHERE UPPER(d.departmentName) LIKE CONCAT('%',UPPER(:departmentName),'%')"
			+ " AND d.status = :status ORDER BY d.departmentName")
	List<Department> findByDepartmentNameContaining(@Param("departmentName") String departmentName,
			@Param("status") String status);

	List<Department> findByStatusOrderByDepartmentNameAsc(String status);

}
