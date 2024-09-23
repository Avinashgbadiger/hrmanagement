package com.calsoft.HR.Management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="meta")
public class Meta {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "emp_number")
   private int empNumber;

    @Column(name="total")
    private int total;


    @Column(name = "qualification_id",insertable = false,updatable = false)
    private int qualificationId;


    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "qualification_id")
    private Qualification qualification;
}
