package com.example.finalproject.repository.crmrepository;

import com.example.finalproject.model.crm.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
