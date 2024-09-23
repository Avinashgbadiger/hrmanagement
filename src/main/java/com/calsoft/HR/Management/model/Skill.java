package com.calsoft.HR.Management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "skill")
public class Skill {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private int id;
    @Column(name = "skill_name")
    private String name;
    @Column(name = "skill_description")
    private String description;

   @Column(name = "empData_id",insertable = false,updatable = false)
    private int empDataId;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "empData_id")
    EmpData empData;
}
