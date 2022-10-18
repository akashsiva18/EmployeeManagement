package com.ideas2it.employee.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * <h2>EmployeeDTO</h2>
 * <p>
 * It is a model class for employee contains common details of employees
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/


public class EmployeeDTO {

    private int id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-d")
    private LocalDate dateOfBirth;
    private String gender;
    private QualificationDTO qualificationDTO;
    private String address;
    private long mobileNumber;
    private String emailId;
    @DateTimeFormat(pattern = "yyyy-MM-d")
    private LocalDate dateOfJoining;
    private RoleDTO roleDTO;


    public EmployeeDTO(int id, String name, LocalDate dateOfBirth, String gender, QualificationDTO qualificationDTO, String address, long mobileNumber, String emailId, LocalDate dateOfJoining, RoleDTO roleDTO) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.qualificationDTO = qualificationDTO;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.dateOfJoining = dateOfJoining;
        this.roleDTO = roleDTO;
    }

    public EmployeeDTO() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public QualificationDTO getQualificationDTO() {
        return qualificationDTO;
    }

    public void setQualificationDTO(QualificationDTO qualificationDTO) {
        this.qualificationDTO = qualificationDTO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }
}
    