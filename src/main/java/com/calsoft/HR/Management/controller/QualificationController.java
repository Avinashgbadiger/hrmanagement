package com.calsoft.HR.Management.controller;

import com.calsoft.HR.Management.model.Qualification;
import com.calsoft.HR.Management.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QualificationController {


    @Autowired
    private QualificationService qualificationService;


    @PostMapping("/create")
    public ResponseEntity<?> createQualification(@RequestBody Qualification qualification) {
        Qualification qualification1 = this.qualificationService.creatingQualification(qualification);
        return new ResponseEntity<>(qualification, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id)
    {
        Qualification qualificationById = this.qualificationService.getQualificationById(id);
        return new ResponseEntity<>(qualificationById,HttpStatus.ACCEPTED);
    }


}
