package com.example.finalproject.service.applicationService;

import com.example.finalproject.model.application.Application;
import com.example.finalproject.model.application.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {
    List<Job> jobList();
    Application setDateToApplication(Application application);
    List<Application> getAllApplication();
    Application saveApplication(Application application);
    Application getApplicationById(long Id);
    void deleteApplicationId(long Id);
}
