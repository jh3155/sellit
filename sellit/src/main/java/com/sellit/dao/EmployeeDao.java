package com.sellit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sellit.persistence.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

	@Query(value = "SELECT e FROM Employee e WHERE UPPER(e.employeeName) LIKE CONCAT('%',UPPER(:employeeName),'%')"
			+ " AND e.status = :status ORDER BY e.employeeName")
	List<Employee> findByEmployeeNameContaining(@Param("employeeName") String employeeName,
			@Param("status") String status);

}