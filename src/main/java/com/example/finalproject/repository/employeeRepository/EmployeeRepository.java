package com.example.finalproject.repository.employeeRepository;

import com.example.finalproject.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
