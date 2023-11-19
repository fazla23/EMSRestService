package com.example.finalproject.model.leave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "leaves")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Entity
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long leaveId;
    private String description;
    private String leaveType;
    private LocalDate durationStart;
    private LocalDate durationEnd;
    private int numberOfDays;
    private String mode;
    private String employee;
    private String department;
    private String reportedInLastPayslip;
    private String commentByManager;
    private String status;
}
