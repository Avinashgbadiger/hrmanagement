package com.calsoft.HR.Management.service;

import com.calsoft.HR.Management.model.EmpData;
import com.calsoft.HR.Management.model.Meta;
import com.calsoft.HR.Management.model.Qualification;
import com.calsoft.HR.Management.model.Skill;
import com.calsoft.HR.Management.repository.EmpDataRepository;
import com.calsoft.HR.Management.repository.MetaRepository;
import com.calsoft.HR.Management.repository.QualificationRepository;
import com.calsoft.HR.Management.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {


    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private EmpDataRepository empDataRepository;
    @Override
       public Qualification creatingQualification(Qualification qualification) {
        // Initialize an empty list for EmpData
        List<EmpData> empDataList = qualification.getEmpData();

        // Save the Qualification first to get its ID
        Qualification savedQualification = this.qualificationRepository.save(qualification);

        // Now handle the associated Meta
        Meta meta = qualification.getMeta();
        meta.setQualification(savedQualification); // Link the saved Qualification
        Meta savedMeta = this.metaRepository.save(meta); // Save Meta

        // Update the saved Qualification with the saved Meta
        savedQualification.setMeta(savedMeta);

        // Iterate through EmpData and save associated Skill
        for (EmpData empData : empDataList) {
            Skill skill = empData.getSkill();

            // Set the back-reference in Skill
            if (skill != null) {
                skill.setEmpData(empData); // Ensure the Skill knows about its EmpData
                Skill savedSkill = this.skillRepository.save(skill); // Save Skill
                empData.setSkill(savedSkill); // Update EmpData with the saved Skill
            }

            empData.setQualification(savedQualification); // Link EmpData to Qualification
            this.empDataRepository.save(empData); // Save EmpData
        }

        return savedQualification; // Return the saved Qualification with all associations
    }


    @Override
    public Qualification getQualificationById(int id) {

        Qualification qualification = this.qualificationRepository.findById(id).get();
        return qualification;
    }

    @Override
    public List<Qualification> getAllQualification() {
        return List.of();
    }

    @Override
    public Qualification updateQualification() {
        return null;
    }

    @Override
    public void deletingQualification() {

    }
}
