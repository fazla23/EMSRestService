package com.example.finalproject.model.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Table(name = "employee")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;
    private String employeeName;
    private String workingAddress;
    private String workMobile;
    private String workLocation;
    private String workEmail;
    private String workPhone;
    private String department;
    private String jobTitle;
    private String manager;
    private String coach;
    private String other;
    private String nationality;
    private String identificationNo;
    private String passportNo;
    private String bankAccountNumber;
    private String gender;
    private String maritalStatus;
    private String homeAddress;
    private String dateOfBirth;

}
