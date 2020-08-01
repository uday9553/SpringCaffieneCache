package com.uday.springcrudopr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uday.springcrudopr.model.Employee;
import com.uday.springcrudopr.repository.EmployeeRepository;

@Service
@CacheConfig(cacheNames = {"employee"})
public class EmployeeService {
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository){
		this.employeeRepository=employeeRepository;
		
	}
	
	@Cacheable(unless = "#result == null")
	public ResponseEntity<List<Employee>> fetchAll(){
		
		System.out.println("hit fetchAll()");
		List<Employee> eEmployees= employeeRepository.findAll();
		
		return ResponseEntity.ok(eEmployees);
		
	}
	//cacheput annotation will not refresh cache immmediately
	//@CachePut(key="#employee.empid",condition = "#employee.empid != null")
	@Caching(evict = {
            @CacheEvict(value = "employee", allEntries = true)
    })
	public Employee postEmployee(Employee employee) {
		System.out.println("hit putEmployee()");
		return employeeRepository.save(employee);
	}

	@Cacheable(unless = "#result == null")
	public Employee getEmpName(Long id) {
		 System.out.println("hit getEmpName()");
		Optional<Employee> employee= employeeRepository.findById(id);
		Employee emp=employee.get();
		return emp;		
		
	}
	
	@CacheEvict(key="#employee.empid",allEntries = true)
	public Employee putEmployee(Employee employee) {
		System.out.println("hit putEmployee()");
		return employeeRepository.save(employee);
	}
	
	@CacheEvict(key = "#employee",allEntries = true)
    public void  remove(Employee employee) {
       // log.info("Executing removed all customers", customer);
        employeeRepository.delete(employee);;
    }
	
	@Caching(evict = {
            @CacheEvict(value = "employee", allEntries = true)
    })
    
    public void clearAllCaches() {
        System.out.println("Cleared customer cache");
    }

}
