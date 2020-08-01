package com.uday.springcrudopr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="eemployee")
@Data
public class Employee {
	
	@Id
	@Column(name="empid")
	private Long empid;
	
	@Column(name="empname")
	private String empname;
	
	@Column(name="locationcode")
	private String locationcode;
  
	@Column(name="location")
	private String location;
	
	@Column(name="date")
	private String date;
	
	@Column(name="salary")
	private String salary;
  

 
}
