package com.example.finalproject.service.crmService;

import com.example.finalproject.model.crm.CRM;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CRMService {
    List<CRM> getAllCRMInfo();
    CRM saveCRMInfo(CRM employeeInfo);
    CRM getCRMInfoById(long Id);
    void deleteCRMInfoId(long Id);
    CRM getCRMByName(String crm_name);
}