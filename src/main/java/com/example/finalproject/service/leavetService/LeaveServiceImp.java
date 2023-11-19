package com.example.finalproject.service.leavetService;

import com.example.finalproject.model.leave.Leave;
import com.example.finalproject.repository.leaveRepository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaveServiceImp implements LeaveService{
    @Autowired
    LeaveRepository leaveRepository;

    @Override
    public List<Leave> getAllLeave() {
        return leaveRepository.findAll();
    }

    @Override
    public Leave saveLeave(Leave leave) {
        return leaveRepository.save(leave);
    }

    @Override
    public Leave getLeaveById(long Id) {
        return leaveRepository.findByLeaveId(Id);
    }

    @Override
    public void deleteLeaveId(long Id) {
        leaveRepository.deleteById(Id);
    }
}
