package com.calsoft.HR.Management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@lombok.Data
@NoArgsConstructor
@Table(name = "qualification")
public class Qualification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_id")
    private int qualificationId;


    @OneToMany(mappedBy = "qualification",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<EmpData> empData;

    @OneToOne(mappedBy = "qualification",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Meta meta;

    private List<Object> rels; // Adjust the type as needed for rels


}
