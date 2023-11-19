package com.example.finalproject.controller.employeeController;


import com.example.finalproject.model.employee.Employee;

import com.example.finalproject.service.employeeService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


    @ResponseBody
    @GetMapping("/employee")
    public Employee no(){
        return new Employee();
    }


    @GetMapping("/employee/getAll")
    public ResponseEntity<?> getAllEmployee() {
        try {
            List<Employee> employeeList = employeeService.getAllEmployee();
            if (employeeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmpPubInfo(@PathVariable("id") long id) {
        try {
            Optional<Employee> employee = Optional.ofNullable(employeeService.getEmployeeById(id));
            return new ResponseEntity<>(employee,HttpStatus.OK);
            }
        catch (RuntimeException e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/employee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        try {
            return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employee")
    public ResponseEntity<?> update(@RequestBody Employee employee){

        try{
            return new ResponseEntity<>(Optional.ofNullable(employeeService.saveEmployee(employee)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }


}
