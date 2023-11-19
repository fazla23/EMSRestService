package com.example.finalproject.repository.leaveRepository;

import com.example.finalproject.model.leave.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    Leave findByLeaveId(Long leaveId);
}
