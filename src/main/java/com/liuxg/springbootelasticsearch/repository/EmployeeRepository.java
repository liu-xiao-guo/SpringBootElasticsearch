package com.liuxg.springbootelasticsearch.repository;

import com.liuxg.springbootelasticsearch.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {

    List<Employee> findAllEmployeeDetailsFromES();
    List<Employee> findAllUserDataByNameFromES(String name);
    List<Employee> findAllUserDataByNameAndOccupationFromES(String name, String occupation);

}
