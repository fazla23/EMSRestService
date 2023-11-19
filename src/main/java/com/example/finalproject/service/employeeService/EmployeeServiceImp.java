package com.example.finalproject.service.employeeService;

import com.example.finalproject.model.employee.Employee;
import com.example.finalproject.repository.employeeRepository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee){
        Employee new_employee = this.employeeRepository.save(employee);
        if(new_employee.getEmployeeId()>0)
            return new_employee;
        else
            throw new RuntimeException("Employee not saved");
    }

    @Override
    public Employee getEmployeeById(long Id) {
        Employee employee = null;
        Optional<Employee> optional = employeeRepository.findById(Id);
        if(optional.isPresent()){
            employee = optional.get();
        }
        else{
            throw new RuntimeException("Employee not found");
        }
        return employee;
    }

    @Override
    public void deleteEmployeeId(long Id) {
        this.employeeRepository.deleteById(Id);
    }
}
