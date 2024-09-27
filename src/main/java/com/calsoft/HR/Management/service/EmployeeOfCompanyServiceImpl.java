package com.calsoft.HR.Management.service;

import com.calsoft.HR.Management.model.EmployeeOfCompany;
import com.calsoft.HR.Management.model.HrmEmployeePayslipDetail;
import com.calsoft.HR.Management.repository.EmployeeOfCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeOfCompanyServiceImpl implements EmployeeOfCompanyService {

    @Autowired
    private EmployeeOfCompanyRepository employeeOfCompanyRepository;


    @Override
    public EmployeeOfCompany getEmpById(int id) {
        return null;
    }

    @Override
    public EmployeeOfCompany savingEmp(EmployeeOfCompany employeeOfCompany) {
        String ctc = employeeOfCompany.getCtc();
        if (ctc == null) return this.employeeOfCompanyRepository.save(employeeOfCompany);

        double annualSalary = Double.parseDouble(ctc);
        double monthlySalary = annualSalary / 12;
        HrmEmployeePayslipDetail payslipDetails = employeeOfCompany.getHrmEmployeePayslipDetails();

        payslipDetails.setPfMonthlyDeduction(2000);
        payslipDetails.setPfAnnualDeduction(2000 * 12);
        payslipDetails.setTotalSalary(monthlySalary);
        payslipDetails.setEsi(1000);
        payslipDetails.setAnnualSalary(annualSalary);
        payslipDetails.setDate(new Date());

        double hra = calculateHra(employeeOfCompany.getCityType(), annualSalary);
        payslipDetails.setHra(hra);

        payslipDetails.setBasicSalary(monthlySalary - hra - 3000); // Fixed deductions

        String state = employeeOfCompany.getState().toLowerCase();
        if (isValidState(state)) {
            calculateDeductionsAndNetSalary(state, monthlySalary, payslipDetails);
        }

        return employeeOfCompanyRepository.save(employeeOfCompany);


    }

    private boolean isValidState(String state) {
        return List.of("karnataka", "tamil nadu", "maharashtra", "andhra pradesh", "west bengal", "telangana").contains(state);
    }

    private void calculateDeductionsAndNetSalary(String state, double monthlySalary, HrmEmployeePayslipDetail payslipDetail) {
        Map<String, Double> taxThresholds = Map.of(
                "karnataka", 15000.0,
                "maharashtra", 10000.0,
                "west bengal", 10000.0,
                "tamil nadu", 15000.0,
                "telangana", 20000.0,
                "andhra pradesh", 20000.0
        );

        double professionalTax = monthlySalary >= taxThresholds.get(state) ? 200 : 0;
        double totalDeductions = 3000 + professionalTax; // Fixed deductions + professional tax

        payslipDetail.setProfessionalTax(professionalTax);
        payslipDetail.setDeductions(totalDeductions);
        payslipDetail.setNetSalary(monthlySalary - totalDeductions);
    }

    private double calculateHra(String cityType, double annualSalary) {
        return (cityType.toUpperCase().startsWith("M") ? 0.50 : 0.40) * annualSalary / 12;
    }

    @Override
    public List<EmployeeOfCompany> getAllEmp() {
        return List.of();
    }

    @Override
    public EmployeeOfCompany updatingEmp(int id, EmployeeOfCompany employeeOfCompany) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
