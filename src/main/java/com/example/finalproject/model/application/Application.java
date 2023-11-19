package com.example.finalproject.model.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "applications")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applicationId;
    private String applicationName;
    private String applicantName;
    private String stage;
    private String email;
    private String phone;
    private String mobile;
    private String degree;
    private String appliedJob;
    private String department;
    private String responsible;
    private String nextAction;
    private String nextActionDescription;
    private String appreciation;
    private String source;
    private String referredBy;
    private String expectedSalary;
    private String expectedSalaryExtra;
    private String proposedSalary;
    private String proposedSalaryExtra;
    private String availability;
    private String applicationSummary;
    private String status;
    private LocalDate createdDate;
    private LocalTime createdTime;

}
