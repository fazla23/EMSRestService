package com.example.finalproject.model.crm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CRM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="crm_id")
    private Long id;
    @Column(name="crm_name")
    private String name;
    private String country;
    private String state;
    private String city;
    private String street;
    private String website;
    private String tags;
    private String phone;
    private String mobile;
    private String fax;
    private String email;
    private String language;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Contact> contacts;
}
