package com.solvd.university.doc;

import com.solvd.university.structure.Address;

import java.time.*;

public class Passport {

    private String code;
    private int serialNumber;
    private Address address;
    private LocalDate issueDate;

    public Passport(String code, int serialNumber, Address address, LocalDate issueDate) {
        this.code = code;
        this.serialNumber = serialNumber;
        this.address = address;
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Passport№ " + this.code + "" + this.serialNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}