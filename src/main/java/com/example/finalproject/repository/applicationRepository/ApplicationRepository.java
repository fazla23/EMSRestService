package com.example.finalproject.repository.applicationRepository;

import com.example.finalproject.model.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    public Application findApplicationByApplicationId(long applicationId);

    public List<Application> findByDepartmentAndAppliedJob(String department, String appliedJob);
    public List<Application> findByAppliedJob(String appliedJob);


//    @Query("SELECT * FROM application where department=?1 and applied_job=?2")
//    public List<Application> jobTitleAndDepartmentTotalEmployees(String jobTitle, String department);

}
