package com.test.couchbasedatasrv.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@Data
@JsonInclude(Include.NON_NULL)
public class Department {

	@Id
	@Field("deptId")
	private String departmentId;
	
	@Field("dname")
	private String name;
	
	@Field("loc")
	private String location;
}
