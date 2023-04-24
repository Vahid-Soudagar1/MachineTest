package com.example.test.models;

public class BankInfoModel {
    private int id;
    private String bankName, branchName, accountNo, ifscCode, photoPath;

    public BankInfoModel() {

    }

    public BankInfoModel(int id, String bankName, String branchName, String accountNo, String ifscCode, String photoPath) {
        this.id = id;
        this.bankName = bankName;
        this.branchName = branchName;
        this.accountNo = accountNo;
        this.ifscCode = ifscCode;
        this.photoPath = photoPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
