package com.example.test.models;

public class EmployeeInfoModel {
    private int id;
    private String employeeNo, employeeName, designation, accountType, experience;

    public EmployeeInfoModel() {

    }

    public EmployeeInfoModel(int id, String employeeNo, String employeeName, String designation, String accountType, String experience) {
        this.id = id;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.designation = designation;
        this.accountType = accountType;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
