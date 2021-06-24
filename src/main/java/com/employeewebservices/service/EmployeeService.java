package com.employeewebservices.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeewebservices.entity.Employee;
import com.employeewebservices.repository.EmployeeRepository;

/*
 * @author sakshilavalkar
 * This class is a service class for employee which handles employee services
 */
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	/*
	 * @param Employee employee
	 * This method saves employee data into the database
	 * @return Employee
	 */
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}
	
	
	/*
	 * @param List<Employee> employee
	 * This method saves list of employee's data into the database
	 * @return List<Employee>
	 */
	public List<Employee> saveEmployees(List<Employee> employeeList) {
		return repository.saveAll(employeeList);
	}
	
	
	/*
	 * @param int employeeId
	 * This method get particular employee data by id from the database
	 * @return Employee
	 */
	public Employee getEmployeeById(int employeeId) {
		return repository.findById(employeeId).orElse(null);
	}
	

	/*
	 * This method get all the employee's data from the database
	 * @return List<Employee>
	 */
	public List<Employee> getEmployees(){
		return repository.findAll();
	}
	
	
	/*
	 * @param Employee employee
	 * This method update the employee data into the database
	 * @return Employee
	 */
	public Employee updateEmployee(Employee employee) {
		Employee existingEmployee=repository.findById(employee.getEmpCode()).orElse(null);
		existingEmployee.setEmpName(employee.getEmpName());
		existingEmployee.setEmpEmail(employee.getEmpEmail());
		existingEmployee.setEmpLocation(employee.getEmpLocation());
		existingEmployee.setEmpDOB(employee.getEmpDOB());
		return repository.save(existingEmployee);
	}
	
}