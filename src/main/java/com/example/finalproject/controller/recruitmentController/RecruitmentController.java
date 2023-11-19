package com.example.finalproject.controller.recruitmentController;
import com.example.finalproject.model.application.Application;
import com.example.finalproject.model.application.Job;
import com.example.finalproject.model.crm.CRM;
import com.example.finalproject.model.employee.Employee;
import com.example.finalproject.service.applicationService.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class RecruitmentController {

    @Autowired
    ApplicationService applicationService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    @GetMapping("/application/jobList")
    public ResponseEntity<?> jobList(){
        try{
            List<Job> jobList = applicationService.jobList();
            if(jobList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(jobList,HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/application/getAll")
    public ResponseEntity<?> getAllApplication() {
        try {
            List<Application> applicationList = applicationService.getAllApplication();
            if (applicationList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(applicationList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/application/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            Optional<Application> application = Optional.ofNullable(applicationService.getApplicationById(id));
            return new ResponseEntity<>(application,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/application")
    public ResponseEntity<?> save(@RequestBody Application application){
        try {
            Application app = applicationService.saveApplication(applicationService.setDateToApplication(application));
            return new ResponseEntity<>(app, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/application")
    public ResponseEntity<?> update(@RequestBody Application application){
        Application findApplication = applicationService.getApplicationById(application.getApplicationId());
        application.setCreatedDate(findApplication.getCreatedDate());
        application.setCreatedTime(findApplication.getCreatedTime());
        try {
            Application app = applicationService.saveApplication(application);
            return new ResponseEntity<>(app, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/application/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            applicationService.deleteApplicationId(id);
            return ResponseEntity.ok().body("Application has been deleted");

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
