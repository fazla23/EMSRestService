package com.example.finalproject.repository.crmrepository;

import com.example.finalproject.model.crm.CRM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CRMRepository extends JpaRepository<CRM,Long> {
    CRM findByName(String crm_name);
}