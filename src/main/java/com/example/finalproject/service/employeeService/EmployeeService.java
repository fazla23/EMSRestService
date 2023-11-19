package com.example.finalproject.service.employeeService;

import com.example.finalproject.model.employee.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> getAllEmployee();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(long Id);
    void deleteEmployeeId(long Id);
}
