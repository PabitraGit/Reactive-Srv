package com.test.couchbasedatasrv.repo;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.test.couchbasedatasrv.model.Employee;

@Repository
public interface EmployeeRepo extends CouchbaseRepository<Employee, String>{
}
