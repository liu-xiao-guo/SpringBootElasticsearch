package com.liuxg.springbootelasticsearch.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuxg.springbootelasticsearch.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Employee> findAllEmployeeDetailsFromES() {
        Employee employee = new Employee("liuxg", "male", "engineer", 10000);
        List<Employee> list = new ArrayList<>();
        list.add(employee);
        return list;
    }

    @Override
    public List<Employee> findAllUserDataByNameFromES(String name) {
        return null;
    }

    @Override
    public List<Employee> findAllUserDataByNameAndOccupationFromES(String name, String occupation) {
        return null;
    }
}
