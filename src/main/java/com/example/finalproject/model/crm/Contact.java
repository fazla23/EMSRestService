package com.example.finalproject.model.crm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contact_id")
    private long id;
    @Column(name="contact_name")
    private String contactName;
    private String title;
    private String jobPosition;
    private String email;
    private String phone;
    private String mobile;
    private String notes;
    private String crm_name;

}
