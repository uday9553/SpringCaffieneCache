package com.uday.springcrudopr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uday.springcrudopr.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	//Optional<Employee> findById(long id);
	//List<Employee> findByEmployeeIdentityEmpid(@Param(value="empid")String empid); 
	//List<Employee> findByEmpname(@Param(value="empname")String empname);
}
