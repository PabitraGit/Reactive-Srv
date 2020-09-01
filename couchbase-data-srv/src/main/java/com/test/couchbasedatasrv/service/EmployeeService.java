package com.test.couchbasedatasrv.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.couchbasedatasrv.model.Employee;
import com.test.couchbasedatasrv.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;

	public Employee createEmployee(Employee empEntity) {
		empEntity.setEmployeeId(generatEmpId(empEntity));
		empEntity.setDepartment(empEntity.getDepartment().stream()
		        .map(dept -> {
			        dept.setDepartmentId(getDeptId());
			        return dept;
		        })
		        .collect(Collectors.toList()));
		return employeeRepo.save(empEntity);
	}

	private String generatEmpId(Employee empEntity) {
		StringBuilder empId = new StringBuilder();
		empId.append(empEntity.getName().charAt(0));
		empId.append(empEntity.getName().charAt(1));
		empId.append(empEntity.getHiredate().getTime());
		return empId.toString();
	}

	private String getDeptId() {
		return String.valueOf(System.currentTimeMillis());
	}
	
}
