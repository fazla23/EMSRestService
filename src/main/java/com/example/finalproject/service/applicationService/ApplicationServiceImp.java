package com.example.finalproject.service.applicationService;

import com.example.finalproject.model.application.Application;
import com.example.finalproject.model.application.Job;
import com.example.finalproject.repository.applicationRepository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImp implements  ApplicationService{

    @Autowired
    private ApplicationRepository applicationRepository;
    private List<Job> jobList = new ArrayList<>();

    public Application setDateToApplication(Application application){
        application.setCreatedDate(LocalDate.now());
        application.setCreatedTime(LocalTime.now());
        return application;
    }

    public List<Job> jobList(){
        jobList.clear();
        List<String> jobTitle = new ArrayList<>();

        createJobList("Chief Executive Officer","Management");
        createJobList("Chief Technical Officer","Research & Development");
        createJobList("Consultant","Professional Services");
        createJobList("Experienced Developer","Research & Development");
        createJobList("Human Resource Manager","Administration");
        createJobList("Marketing and Community Manager","Sales");
        createJobListSingle("Trainee");

        return jobList;
    }

    public void createJobListSingle(String jobTitle){
        List<Application> listOfEmployeeByDepartmentAndAppliedJob = new ArrayList<>();

        listOfEmployeeByDepartmentAndAppliedJob = applicationRepository.findByAppliedJob(jobTitle);

        Job job = new Job();

        job.setDepartment("");
        job.setJobTitle(jobTitle);
        int numberOfEmployeeCount=0;
        for(Application employee: listOfEmployeeByDepartmentAndAppliedJob){
            if((employee.getStage().equals("Contract Signed"))){
                numberOfEmployeeCount++;
            }
        }
        job.setCurrentNumberOfEmployees(numberOfEmployeeCount);
        int expectedNewEmployees = listOfEmployeeByDepartmentAndAppliedJob.size()-numberOfEmployeeCount;
        job.setExpectedNewEmployees(expectedNewEmployees);
        if(expectedNewEmployees==0){
            job.setStatus("Recruitment Not In Progress");
        }
        else{
            job.setStatus("Recruitment in Progress");
        }
        jobList.add(job);
    }

    public void createJobList(String jobTitle, String department){
        List<Application> listOfEmployeeByDepartmentAndAppliedJob = new ArrayList<>();

        listOfEmployeeByDepartmentAndAppliedJob = applicationRepository.findByDepartmentAndAppliedJob(department,jobTitle);

        Job job = new Job();

        job.setDepartment(department);
        job.setJobTitle(jobTitle);
        int numberOfEmployeeCount=0;
        for(Application employee: listOfEmployeeByDepartmentAndAppliedJob){
            if((employee.getStage().equals("Contract Signed"))){
                numberOfEmployeeCount++;
            }
        }
        job.setCurrentNumberOfEmployees(numberOfEmployeeCount);
        int expectedNewEmployees = listOfEmployeeByDepartmentAndAppliedJob.size()-numberOfEmployeeCount;
        job.setExpectedNewEmployees(expectedNewEmployees);
        if(expectedNewEmployees==0){
            job.setStatus("Recruitment Not In Progress");
        }
        else{
            job.setStatus("Recruitment In Progress");
        }
        jobList.add(job);
    }

    public void updating(Application application){
        Application findApplication = applicationRepository.findApplicationByApplicationId(application.getApplicationId());
        application.setCreatedDate(findApplication.getCreatedDate());
        application.setCreatedTime(findApplication.getCreatedTime());

        if(application.getStage().equals("Contract Signed")){

        }
        applicationRepository.save(application);
    }

    @Override
    public List<Application> getAllApplication() {
        return applicationRepository.findAll();
    }

    @Override
    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application getApplicationById(long Id) {
        return applicationRepository.findApplicationByApplicationId(Id);
    }

    @Override
    public void deleteApplicationId(long Id) {
        applicationRepository.deleteById(Id);
    }
}
