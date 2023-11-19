package com.example.finalproject.model.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Job {
    private String department;
    private int currentNumberOfEmployees;
    private int expectedNewEmployees;
    private int hiredEmployees;
    private String status;
    private String jobTitle;
}
