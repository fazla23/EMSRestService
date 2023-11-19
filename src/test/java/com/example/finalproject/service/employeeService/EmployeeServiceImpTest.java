package com.example.finalproject.service.employeeService;

import com.example.finalproject.model.employee.Employee;
import com.example.finalproject.repository.employeeRepository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceImpTest {
    private EmployeeRepository employeeRepository;

    private EmployeeServiceImp employeeServiceImp;

    Employee employee = new Employee(1l,"abc","abc","abc","abc","abc",
            "abc","abc","abc","abc","abc","abc","abc",
            "abc","abc","abc","abc","abc","abc","abc");


    @BeforeEach
    void setupService(){
        employeeRepository = mock(EmployeeRepository.class);
        employeeServiceImp = new EmployeeServiceImp(employeeRepository);
    }

    @Test
    void getAllEmployeeTest(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> new_emp_list = employeeServiceImp.getAllEmployee();

        assertNotNull(new_emp_list);
        assertEquals(employeeList,new_emp_list);
    }

    @Test
    void saveEmployeeSuccessfulTest(){

        when(employeeRepository.save(any())).thenReturn(employee);

        Employee new_employee = employeeServiceImp.saveEmployee(employee);

        assertNotNull(new_employee);
        assertEquals(1l,new_employee.getEmployeeId());
        assertEquals("abc",new_employee.getEmployeeName());
    }

    @Test
    void saveEmployeeUSuccessfulTest(){
        Employee employee_empty = new Employee();

        when(employeeRepository.save(any())).thenReturn(employee_empty);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeServiceImp.saveEmployee(employee_empty);
        });

        assertNotNull(exception);
        Assertions.assertEquals("Employee not saved", exception.getMessage());
    }

    @Test
    void getEmployeeByIdSuccessful(){
        Optional<Employee> optional;
        when(employeeRepository.findById(1l)).thenReturn(Optional.ofNullable(employee));

        optional = Optional.ofNullable(employeeServiceImp.getEmployeeById(1l));

        assertNotNull(optional);
        assertEquals(employee,optional.get());
    }

    @Test
    void getEmployeeByIdUnsuccessful(){

        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeServiceImp.getEmployeeById(1l);
        });

        assertNotNull(exception);
        Assertions.assertEquals("Employee not found", exception.getMessage());

    }

    @Test
    void deleteEmployeeById(){

        employeeServiceImp.deleteEmployeeId(1l);

        verify(employeeRepository).deleteById(1l);
    }


}