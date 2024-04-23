package com.harsh.springboot.EmployeeRepository.Service;

import com.harsh.springboot.EmployeeRepository.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(Integer theId);

    Employee save(Employee theEmployee);

    void deleteById(Integer theId);

}
