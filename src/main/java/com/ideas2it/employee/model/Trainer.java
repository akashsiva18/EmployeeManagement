package com.ideas2it.employee.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import com.ideas2it.employee.util.DateUtil;
import javax.persistence.*;

/**
 * <h2>Trainer</h2>
 * <p>
 * It is a model class for trainer contains details of trainer
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
@Entity(name = "trainer")
public class Trainer extends Employee{

    @Column(name = "experience")
    private int experience;
    @Transient
    private int noOfTrainees;
    @ManyToMany(mappedBy = "trainers")
    private Set<Trainee> trainees;

    /**
     * <p>
     * It is a constructor for Trainer class.
     * </p>
     *
     * @param {@link Employee} employee 
     *               - it is an object of employee that contains common details of employee
     * @param {@link int} experience - experience of trainer
     *
     **/
    public Trainer(Employee employee) {
        this.experience = DateUtil.computeYears(employee.getDateOfJoining(),LocalDate.now());
    }

    public Trainer(String name, LocalDate dateOfBirth, String gender, Qualification qualification,
                   String address, Long mobileNumber, String emailId, LocalDate dateOfJoining,
                   Role role, int experience, Set<Trainee> trainees) {
        super(name, dateOfBirth, gender, qualification, address, mobileNumber, emailId, dateOfJoining, role);
        this.experience = experience;
        this.trainees = trainees;
    }

    /**
     * <p>
     * It is a default constructor.
     * </p>
     *
     **/
    public Trainer(){
    }


    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperience() {
	return experience;
    }

    public int getNoOfTrainees() {
        return noOfTrainees;
    }

    public void setNoOfTrainees(int noOfTrainees) {
        this.noOfTrainees = noOfTrainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
   }

    public Set<Trainee> getTrainees() {
        return trainees;
    }

    /**
     * <p>
     * override toString method
     * </p>
     *
     * @return {@link String} return formatted string.
     **/
    @Override
    public String toString() {
        int size = trainees.size();
        int age = DateUtil.computeYears(getDateOfBirth(),LocalDate.now());
	return "\nID of Employee : " + getId() + "\nName of the Trainer : "
               + getName() + "\nAge : " + age + "\nGender : " + getGender()
               + "\nQualifications : " + getQualification() +"\nAddress : "
               + getAddress() + "\nMobile Number : " + getMobileNumber()
               + "\nEmail Id : " + getEmailId() + "\nDate Of Joining : "
               + getDateOfJoining().format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
               + "\nTraining Experience : " + getExperience() + "\nNo Of Trainees : " + size;
    }

    /**
     * <p>
     * override the hashCode method. to generate the hashcode for the trainee object as ID * 32.
     * </p>
     *
     * @return {@link int} return hashcode of all object as 0;
     **/
    @Override
    public int hashCode() {
        return getId() * 32;
    }

}

    
