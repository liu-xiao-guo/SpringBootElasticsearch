package com.liuxg.springbootelasticsearch.controller;

import com.liuxg.springbootelasticsearch.entity.Employee;
import com.liuxg.springbootelasticsearch.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value ="/allemployees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployeeInfo();
    }

    @GetMapping(value ="/allemployees/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getUserByName(@PathVariable String name){
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping(value ="/allemployees/{name}/{occupation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getUserByNameAndOccupation(@PathVariable String name, @PathVariable String occupation){
        return employeeService.getEmployeesByNameAndOccupation(name, occupation);
    }

}