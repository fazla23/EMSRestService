package com.example.finalproject.controller.employeeController;

import com.example.finalproject.model.employee.Employee;
import com.example.finalproject.repository.employeeRepository.EmployeeRepository;
import com.example.finalproject.service.employeeService.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    EmployeeService employeeService;


    @LocalServerPort
    int randomPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    Employee employee = new Employee(1l,"abc","abc","abc","abc","abc",
            "abc","abc","abc","abc","abc","abc","abc",
            "abc","abc","abc","abc","abc","abc","abc");


    @Test
    void testGetEmployeeById() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomPort + "/api/employee/1";
        URI uri = new URI(baseUrl);
        when(employeeService.getEmployeeById(1l)).thenReturn(employee);
        ResponseEntity<Employee> response = testRestTemplate.getForEntity(uri,Employee.class);

        assertNotNull(response);
        assertEquals(1, response.getBody().getEmployeeId());
        assertEquals("abc", response.getBody().getEmployeeName());
        assertEquals("abc", response.getBody().getDepartment());
    }

    @Test
    void saveEmployeeSuccesTest() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomPort + "/api/employee";
        URI uri = new URI(baseUrl);
        HttpEntity<Employee> entity = new HttpEntity<>(employee);

        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<Employee> response = testRestTemplate.postForEntity(uri,entity,Employee.class);

        assertNotNull(response);
        assertEquals(1, response.getBody().getEmployeeId());
        assertEquals("abc", response.getBody().getEmployeeName());
        assertEquals("abc", response.getBody().getDepartment());
    }

    @Test
    void testGetAllEmployeeSuccess() throws URISyntaxException {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        final String baseUrl = "http://localhost:" + randomPort + "/api/employee/getAll";
        URI uri = new URI(baseUrl);
        when(employeeService.getAllEmployee()).thenReturn(employeeList);
        ResponseEntity<?> response = testRestTemplate.getForEntity(uri,List.class);

        assertNotNull(response);
        assertEquals(200,response.getStatusCodeValue());
//        assertEquals(employeeList,response);
    }

//    @Test
//    void saveEmployeeSuccesTest() throws URISyntaxException {
//        final String baseUrl = "http://localhost:" + randomPort + "/api/employee";
//        URI uri = new URI(baseUrl);
//        HttpEntity<Employee> entity = new HttpEntity<>(employee);
//
//        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);
//
//        ResponseEntity<Employee> response = testRestTemplate.put(uri,entity,Employee.class);
//
//        assertNotNull(response);
//        assertEquals(1, response.getBody().getEmployeeId());
//        assertEquals("abc", response.getBody().getEmployeeName());
//        assertEquals("abc", response.getBody().getDepartment());
//    }

    @Test
    @Disabled
    void testGetEmployeeIdUnsuccessful() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomPort + "/api/employee/1";
        URI uri = new URI(baseUrl);
        when(employeeService.getEmployeeById(2l)).thenThrow(new RuntimeException());

        Exception exception = assertThrows(RuntimeException.class, () ->
                testRestTemplate.getForEntity(uri,Employee.class)
        );
        ResponseEntity<Employee> response = testRestTemplate.getForEntity(uri,Employee.class);
        assertNotNull(exception);
        assertEquals(null,response.getBody());

    }

}
