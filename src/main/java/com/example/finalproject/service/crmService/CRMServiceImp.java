package com.example.finalproject.service.crmService;
import com.example.finalproject.model.crm.CRM;
import com.example.finalproject.repository.crmrepository.CRMRepository;
import com.example.finalproject.service.crmService.CRMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CRMServiceImp implements CRMService {

    @Autowired
    CRMRepository crmRepository;

    public CRMServiceImp(CRMRepository empPubRepo) {
        this.crmRepository = crmRepository;
    }

    @Override
    public CRM getCRMByName(String crm_name){
        return crmRepository.findByName(crm_name);
    }

    @Override
    public List<CRM> getAllCRMInfo() {

        return crmRepository.findAll();
    }

    @Override
    public CRM saveCRMInfo(CRM crmInfo) {
        try{
            this.crmRepository.save(crmInfo);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return  crmInfo;
    }

    @Override
    public CRM getCRMInfoById(long Id) {
        CRM crmInfo = null;
        Optional<CRM> optional = this.crmRepository.findById(Id);
        if(optional.isPresent()){
            crmInfo = optional.get();
        }
        else{
            throw new RuntimeException("CRM not found for id::"+ Id);
        }
        return crmInfo;
    }

    @Override
    public void deleteCRMInfoId(long Id) {
        this.crmRepository.deleteById(Id);
    }
}

