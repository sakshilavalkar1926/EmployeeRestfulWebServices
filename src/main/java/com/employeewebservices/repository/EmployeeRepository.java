package com.employeewebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeewebservices.entity.Employee;

/*
 * @author sakshilavalkar
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
}
