package com.test.couchbasedatasrv.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.test.couchbasedatasrv.model.Employee;
import com.test.couchbasedatasrv.service.EmployeeService;

@RestController
@Tag(name = "Employee API's", description = "Employee API's")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	private static final ResponseEntity<Employee> NO_CONTENT = ResponseEntity.noContent()
	        .build();
	
	
	/**
	 * {@link PostMapping} to create employee
	 *
	 * @return {@link Employee}
	 */
	@Operation(description = "Create a new employee", summary = "Create a new employee", responses = {
	        @ApiResponse(responseCode = "201",
	                     content = @Content(schema = @Schema(implementation = Employee.class)),
	                     description = "Employee Created Successfully"),
	        @ApiResponse(responseCode = "400", content = @Content,
	                     description = "Bad request. Something wrong was sent from the client."),
	        @ApiResponse(responseCode = "500", content = @Content,
	                     description = "An internal server error occurred.")})
	@PostMapping(value = "/emp/new",
	             produces = {MediaType.APPLICATION_STREAM_JSON_VALUE,
	                     MediaType.APPLICATION_JSON_VALUE},
	             consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE,
	                     MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Employee>
	       createEmployee(@Parameter(required = true, name = "requestBody",
	                             description = "Employee") @NotNull @RequestBody final Employee empEntity) {
		 return new ResponseEntity<>(empService.createEmployee(empEntity), HttpStatus.CREATED);
	}
	
}
