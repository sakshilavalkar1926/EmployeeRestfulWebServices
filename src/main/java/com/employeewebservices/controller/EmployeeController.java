package com.employeewebservices.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.employeewebservices.entity.Employee;

import com.employeewebservices.service.EmployeeService;

/*
 * author Sakshilavalkar
 * This is a controller class which handles all the mappings of employee
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	/*
	 * @param Employee employee
	 * This method handles mapping for "/addEmployee"
	 * @return Employee
	 */
	@PostMapping("/addEmployee")
	@ResponseBody
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	/*
	 * @param List<Employee> employeeList
	 * This method handles mapping for "/addEmployees"
	 * @return List<Employee>
	 */
	@PostMapping("/addEmployees")
	@ResponseBody
	public List<Employee> addEmployees(@RequestBody List<Employee> employeeList){
		return employeeService.saveEmployees(employeeList);
	}
	
	/*
	 * @param List<Employee> employeeList
	 * This method handles mapping for "/employees/import"
	 */
	@PostMapping("/employees/import")
	public void importFromCSV(@RequestBody List<Employee> employees) throws IOException {
		employeeService.saveEmployees(employees);
		System.out.println("data saved successfully");
	}
	
	/*
	 * This method handles mapping for "/getEmployees"
	 * @return List<Employee>
	 */
	@GetMapping("/getEmployees")
	@ResponseBody
	public List<Employee> getAllEmployees(){
		return employeeService.getEmployees();
	}
	
	/*
	 * @param int employeeId
	 * This method handles mapping for "/getEmployee/{employeeId}"
	 * @return Employee
	 */
	@GetMapping("/getEmployee/{employeeId}")
	@ResponseBody
	public Employee getEmployee(@PathVariable int employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}
	
	/*
	 * @param HttpServletResponse response
	 * This method handles mapping for "/employees/export"
	 */
	@GetMapping("/employees/export")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		System.out.println("in export");
		response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employees_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
         
        List<Employee> listEmployees = employeeService.getEmployees();
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Employee Code", "Name", "Email", "Date of birth", "Employee Location"};
        String[] nameMapping = {"empCode", "empName", "empEmail", "empDOB", "empLocation"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (Employee employee : listEmployees) {
            csvWriter.write(employee, nameMapping);
        }
         
        csvWriter.close();
	}
	
	/*
	 * @param Employee employee
	 * This method handles mapping for "/updateEmployee"
	 * @return Employee
	 */
	@PutMapping("/updateEmployee")
	@ResponseBody
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
	
	
	
	
}
