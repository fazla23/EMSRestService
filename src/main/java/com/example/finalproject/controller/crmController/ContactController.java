package com.example.finalproject.controller.crmController;

import com.example.finalproject.model.crm.CRM;
import com.example.finalproject.model.crm.Contact;
import com.example.finalproject.repository.crmrepository.CRMRepository;
import com.example.finalproject.repository.crmrepository.ContactRepository;
import com.example.finalproject.service.crmService.CRMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ContactController {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    CRMService crmService;
    @GetMapping("/getAllContact")

    public ResponseEntity<?> getAllContact(){
        try {
            List<Contact> contactList = contactRepository.findAll();
            if (contactList.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contactList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getContact/{id}")
    public ResponseEntity<?> getContact(@PathVariable("id") long id) {
        try {
            Optional<Contact> contact = contactRepository.findById(id);
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/contact")
    public ResponseEntity<?> saveContact(@RequestBody Contact contact){
        String crm_name = contact.getCrm_name();
        CRM  crm = crmService.getCRMByName(crm_name);
        List<Contact> crm_contactList = crm.getContacts();
        crm_contactList.add(contact);
        try {
            Contact new_contact = contactRepository.save(contact);
            return new ResponseEntity<>(new_contact, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteContact/{id}")
    public ResponseEntity<HttpStatus> deleteCRMInfo(@PathVariable("id") long id) {
        try {
            contactRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
