package com.example.finalproject.controller.employeeController;


import com.example.finalproject.model.employee.Employee;
import com.example.finalproject.service.employeeService.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeControllerUnitTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    Employee employee = new Employee(1l,"abc","abc","abc","abc","abc",
            "abc","abc","abc","abc","abc","abc","abc",
            "abc","abc","abc","abc","abc","abc","abc");


    @Test
    void getAllEmployee() {
        when(employeeService.getAllEmployee()).thenReturn
                (List.of(employee));

        ResponseEntity<?> employeelist = employeeController.getAllEmployee();
        assertNotNull(employeelist);
        assertEquals(200,employeelist.getStatusCodeValue());
        assertEquals(List.of(employee),employeelist.getBody());
    }

    @Test
    void getAllEmployeeUnsuccessfulWithNoEmployee(){
        when(employeeService.getAllEmployee()).thenReturn
                (List.of());

        ResponseEntity<?> employeelist = employeeController.getAllEmployee();
        assertNotNull(employeelist);
        assertEquals(204,employeelist.getStatusCodeValue());
        assertEquals(null,employeelist.getBody());
    }

    @Test
    void getAllEmployeeUnsuccessfulwithException(){
        when(employeeService.getAllEmployee()).thenThrow(new RuntimeException("Fail Case"));

        try{
            ResponseEntity<?> employeelist = employeeController.getAllEmployee();
        }
        catch (RuntimeException runtimeException){
            assertEquals("Fail Case", runtimeException.getMessage());
        }
    }

    @Test
    void saveEmployeeSuccessful(){
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<?> new_employee = employeeController.addEmployee(employee);
        assertNotNull(new_employee);
        assertEquals(201,new_employee.getStatusCodeValue());
        assertEquals(employee,new_employee.getBody());
    }

    @Test
    void saveEmployeeUnsuccessful(){
        when(employeeService.saveEmployee(any(Employee.class))).thenThrow(new NullPointerException("Employee Not saved"));

        try{
            ResponseEntity<?> new_employee = employeeController.addEmployee(employee);
        }
        catch (NullPointerException e){
            assertEquals("Employee Not saved", e.getMessage());
        }
    }

    @Test
    void getEmployeeByIdSuccessful() {
        when(employeeService.getEmployeeById(1l)).thenReturn(employee);

        ResponseEntity<?> new_employee = employeeController.getEmpPubInfo(1l);
        Optional<Employee> emp = (Optional<Employee>) new_employee.getBody();

        assertNotNull(new_employee);
        assertEquals(200,new_employee.getStatusCodeValue());
        assertEquals(employee, emp.get());
    }

    @Test
    void getEmployeeByIdUnsuccessful(){
        when(employeeService.getEmployeeById(1l)).thenThrow(new NullPointerException("Employee Not Found"));

        try{
            ResponseEntity<?> new_employee = employeeController.getEmpPubInfo(1l);
        }
        catch (NullPointerException e){
            assertEquals("Employee Not Found", e.getMessage());
        }
    }

    @Test
    void updateEmployeeSuccessful(){
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<?> new_employee = employeeController.update(employee);
        Optional<Employee> emp = (Optional<Employee>) new_employee.getBody();

        assertNotNull(new_employee);
        assertEquals(200,new_employee.getStatusCodeValue());
        assertEquals(employee, emp.get());
    }

    @Test
    void updateEmployeeUnsuccessful(){
        when(employeeService.saveEmployee(any(Employee.class))).thenThrow(new RuntimeException("Employee Not updated"));

        try{
            ResponseEntity<?> new_employee = employeeController.update(employee);
        }
        catch (RuntimeException e){
            assertEquals("Employee Not updated", e.getMessage());
        }
    }


}
