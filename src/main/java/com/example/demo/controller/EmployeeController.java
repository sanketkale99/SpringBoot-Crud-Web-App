package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	//display employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index"; 
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee employee=new Employee();
		model.addAttribute("employee",employee);
		return "new_employee";
	}
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee")Employee employee) {
		//save emp to db
		employeeService.saveEmployee(employee);
		return"redirect:/";
	}
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
		
		//get empl from service
		Employee employee = employeeService.getEmployeeById(id);
		
		//set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		//call delete emp method
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
		
	}
	
}
