package com.ideas2it.employee.model;

import java.time.LocalDate;

/**
 * <h2>Employee</h2>
 * <p>
 * It is a model class for employee contains common details of employees
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public class Employee {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private Qualification qualification;
    private String address;
    private long mobileNumber;
    private String emailId;
    private LocalDate dateOfJoining;
    private Role role;

    /**
     * <p>
     * It is a constructor for employee class.
     * </p>
     *
     * @param {@link String} name - name of the employee.
     * @param {@link String} dateOfBirth - date of birth of the employee
     * @param {@link String} gender - gender of the employee
     * @param {@link Qualification} qualification - qualification of the employee
     * @param {@link String} address - address of the employee
     * @param {@link Long} mobileNumber - mobile number of the employee
     * @param {@link String} emailId - email id of the employee
     * @param {@link LocalDate} dateOfJoining - date of joining of the employee
     * @param {@link Role} role - role of the employee.
     * 
     **/
    public Employee(String name, LocalDate dateOfBirth, String gender, Qualification qualification,
                    String address, Long mobileNumber, String emailId, LocalDate dateOfJoining, Role role) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.qualification = qualification;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.dateOfJoining = dateOfJoining;
        this.role = role;
    }

    /**
     * <p>
     * It is a copy constructor for employee class that create a copy of the object.
     * </p>
     *
     * @param {@link Employee} employee
     */
    public Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.name;
        this.dateOfBirth = employee.dateOfBirth;
        this.gender = employee.gender;
        this.qualification = employee.qualification;
        this.address = employee.address;
        this.mobileNumber = employee.mobileNumber;
        this.emailId = employee.emailId;
        this.dateOfJoining = employee.dateOfJoining;
        this.role = employee.role;
    }
    
    /**
     * <p>
     * It is a default constructor.
     * </p>
     *
     **/
    public Employee(){
    }

    public void setId(int id) {
	this.id = id;
    }
 
    public int getId() {
	return id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public String getGender() {
	return gender;
    }

    public void setQualification(Qualification qualification) {
	this.qualification = qualification;
    }

    public Qualification getQualification() {
	return qualification;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getAddress() {
	return address;
    }

    public void setMobileNumber(Long mobileNumber) {
	this.mobileNumber = mobileNumber;
    }

    public long getMobileNumber() {
	return mobileNumber;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }
    
    public void setDateOfJoining(LocalDate dateOfJoining) {
	this.dateOfJoining = dateOfJoining;
    }

    public LocalDate getDateOfJoining() {
	return dateOfJoining;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
    
}
    