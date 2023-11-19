package com.example.finalproject.controller.crmController;

import com.example.finalproject.model.crm.CRM;
import com.example.finalproject.service.crmService.CRMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CRMController {

    @Autowired
    CRMService crmService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/getCRMList")
    public ResponseEntity<List<CRM>> getAllCRMInfo() {
        try {
            List<CRM> crmList = crmService.getAllCRMInfo();
            if (crmList.isEmpty()) {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(crmList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/addCRM")
    public ResponseEntity<?> addCRMInfo(@RequestBody CRM crm) {
        try {
            CRM new_crm = crmService.saveCRMInfo(crm);
            return new ResponseEntity<>(new_crm, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/getCRM/{id}")
    public ResponseEntity<?> getCrm(@PathVariable("id") long id) {
        try {
            Optional<CRM> empPubInfo = Optional.ofNullable(crmService.getCRMInfoById(id));
            return new ResponseEntity<>(empPubInfo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/editCRM")
    public ResponseEntity<?> updateCRMInfo(@RequestBody CRM crm) {

        Optional<CRM> crmData = Optional.ofNullable(crmService.getCRMInfoById(crm.getId()));

        if (crmData.isPresent()) {
            CRM new_crm = crmData.get();
            crm.setContacts(new_crm.getContacts());
            return new ResponseEntity<>(this.crmService.saveCRMInfo(crm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/deleteCRM/{id}")
    public ResponseEntity<HttpStatus> deleteCRMInfo(@PathVariable("id") long id) {
        try {
            this.crmService.deleteCRMInfoId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

