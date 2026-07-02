package com.suraj.employee_service.service;

import com.suraj.employee_service.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getEmployees() {

        return Arrays.asList(
                new Employee(1,"Suraj Pandey","DevOps"),
                new Employee(2,"Rahul Sharma","Cloud")
        );

    }

}