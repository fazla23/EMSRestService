package com.example.finalproject.service.leavetService;

import com.example.finalproject.model.leave.Leave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveService {
    List<Leave> getAllLeave();
    Leave saveLeave(Leave employee);
    Leave getLeaveById(long Id);
    void deleteLeaveId(long Id);
}
