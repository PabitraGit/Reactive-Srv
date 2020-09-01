package com.test.couchbasedatasrv.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@Data
@JsonInclude(Include.NON_NULL)
public class Employee {

	@Id
	@Field("empId")
	private String employeeId;
	
	@Field("ename")
	private String name;
	
	@Field("desg")
	private String designation;
	
	@Field("sal")
	private Double salary;
	
	@Field("hiredate")
	private Date hiredate;
	
	@Field("dept")
	private List<Department> department;
	
	
}
