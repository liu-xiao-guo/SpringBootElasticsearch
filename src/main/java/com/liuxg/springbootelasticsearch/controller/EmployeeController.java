package com.liuxg.springbootelasticsearch.controller;

import com.liuxg.springbootelasticsearch.entity.Employee;
import com.liuxg.springbootelasticsearch.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value ="/allemployees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees() {
//        Employee employee = new Employee("liuxg", "male", "engineer", 10000);
//        List<Employee> list = new ArrayList<>();
//        list.add(employee);
//        return list;
        return employeeService.getAllEmployeeInfo();
    }

}
