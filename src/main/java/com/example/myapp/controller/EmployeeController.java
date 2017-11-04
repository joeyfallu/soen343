package com.example.myapp.controller;

import com.example.myapp.model.Employee;
import com.example.myapp.service.EmployeeService;
import com.example.myapp.userCatalog.UserCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserCatalog us1;

	@RequestMapping(value = "/add/employee", method = RequestMethod.GET)
	public Employee addEmployee(@RequestParam("name") String name, @RequestParam("empId") String empId) {

		return employeeService.createEmployee(name, empId);

	}

	@RequestMapping(value = "/remove/employee", method = RequestMethod.GET)
	public String removeEmployee( @RequestParam("empId") String empId) {

		employeeService.deleteEmployee(empId);

		return "Employee removed";
	}

	@RequestMapping(value = "kappa", method = RequestMethod.GET)
	public void kek(@RequestParam("name") String name, @RequestParam("empId") String empId) {

		 us1.test();

	}

}