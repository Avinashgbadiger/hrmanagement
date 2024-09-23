package com.calsoft.HR.Management.service;

import com.calsoft.HR.Management.model.Qualification;

import java.util.List;

public interface QualificationService {

    Qualification creatingQualification(Qualification qualification);

    Qualification getQualificationById(int id);

    List<Qualification> getAllQualification();

    Qualification updateQualification();

    void deletingQualification();
}
