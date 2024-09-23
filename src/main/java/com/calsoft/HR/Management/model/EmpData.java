package com.calsoft.HR.Management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@lombok.Data
@NoArgsConstructor
@Table(name = "emp_data")
public class EmpData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empData_id")
    private int empDataId;

    @Column(name = "years_of_experience")
    private int yearsOfExperience;
    @Column(name = "comments")
    private String comments;

    @OneToOne(mappedBy = "empData", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Skill skill;


    @Column(name = "qualification_id", insertable = false, updatable = false)
    private int qualificationId;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "qualification_id")
    private Qualification qualification;

}
