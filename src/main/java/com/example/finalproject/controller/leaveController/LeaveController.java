package com.example.finalproject.controller.leaveController;

import com.example.finalproject.model.leave.Leave;
import com.example.finalproject.service.leavetService.LeaveService;
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
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    @ResponseBody
    @GetMapping("/leave")
    public Leave no(){
            return new Leave();
        }

        @PreAuthorize("hasAnyRole('USER','ADMIN')")
        @GetMapping("/leave/getAll")
        public ResponseEntity<?> getAllEmployee() {
            try {
                List<Leave> leaveList = leaveService.getAllLeave();
                if (leaveList.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(leaveList, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PreAuthorize("hasAnyRole('USER','ADMIN')")
        @GetMapping("/leave/{id}")
        public ResponseEntity<?> findById(@PathVariable Long id){
            try{
                Optional<Leave> leave = Optional.ofNullable(leaveService.getLeaveById(id));
                return new ResponseEntity<>(leave,HttpStatus.OK);
            }catch (Exception e){
                return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @PreAuthorize("hasAnyRole('USER','ADMIN')")
        @PostMapping("/leave")
        public ResponseEntity<?> save(@RequestBody Leave leave){
            try {
                return new ResponseEntity<>(leaveService.saveLeave(leave), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @PreAuthorize("hasAnyRole('USER','ADMIN')")
        @PutMapping("/leave")
        public ResponseEntity<?> update(@RequestBody Leave leave){
            try {
                Optional<Leave> new_leave = Optional.ofNullable(leaveService.saveLeave(leave));
                return new ResponseEntity<>(new_leave,HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    
}
