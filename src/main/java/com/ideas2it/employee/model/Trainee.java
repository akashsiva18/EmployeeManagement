package com.ideas2it.employee.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import com.ideas2it.employee.util.DateUtil;

import javax.persistence.*;

/**
 * <h2>Trainee</h2>
 * <p>
 * It is a model class for trainee contains details of trainee
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
@Entity
@Table(name = "trainee")
@PrimaryKeyJoinColumn(name = "id")
public class Trainee extends Employee{

    @Column(name = "training_period")
    private int trainingPeriod;

    @Transient
    private List<Integer> trainersId;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "employee_relationship",
            joinColumns = { @JoinColumn(name = "trainee_id") },
            inverseJoinColumns = { @JoinColumn(name = "trainer_id") })
    private Set<Trainer> trainers;

    /**
     * <p>
     * It is a constructor for Trainee class.
     * </p>
     *
     * @param {@link Employee} employee 
     *               - it is an object of employee that contains common details of employee
     * @param {@link int} trainingPeriod - training period of the trainee
     * @param {@link List<Integer>} trainersId - trainer ID who is in-charge for the trainee
     **/
    public Trainee(int trainingPeriod , List<Integer> trainersId) {
        this.trainingPeriod = trainingPeriod;
        this.trainersId = trainersId;
    }

    public Trainee(String name, LocalDate dateOfBirth, String gender,
                   Qualification qualification, String address, Long mobileNumber,
                   String emailId, LocalDate dateOfJoining, Role role, int trainingPeriod,
                   List<Integer> trainersId, Set<Trainer> trainers) {
        super(name, dateOfBirth, gender, qualification, address, mobileNumber, emailId, dateOfJoining, role);
        this.trainingPeriod = trainingPeriod;
        this.trainers = trainers;
    }


    /**
     * <p>
     * It is a default constructor.
     * </p>
     *
     **/
    public Trainee(){
    }

    public void setTrainingPeriod(int trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    public int getTrainingPeriod() {
	return trainingPeriod;
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<Integer> getTrainersId() {
        return trainersId;
    }

    public void setTrainersId(List<Integer> trainersId) {
        this.trainersId = trainersId;
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
        trainersId = new ArrayList<>();
        for (Trainer trainer : trainers) {
            trainersId.add(trainer.getId());
        }
        int age = DateUtil.computeYears(getDateOfBirth(),LocalDate.now());
	return  "\n" + "ID of Employee : " + getId() +"\nName of the Trainee : "
                + getName() + "\nAge : " + age +"\nGender : "
                + getGender() + "\nQualifications : " + getQualification()
                + "\nAddress : " + getAddress() + "\nMobile Number : "
                + getMobileNumber() + "\nEmail Id : " + getEmailId()
                + "\nDate Of Joining : " + getDateOfJoining().format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                + "\nTraining Period : " + getTrainingPeriod() + "\nTrainers : " 
                + trainersId.toString().replaceAll("[\\[\\]]","");
    }


    /**
     * <p>
     * override the equals method. To check the required field.
     * </p>
     *
     * @param {@link Object) o.
     *
     * @return {@link boolean} return true if all the condition satisfied else false.
     **/
    @Override
    public boolean equals(Object o) {
        Trainee trainee = (Trainee)o;
        if (!(this.getId() == trainee.getId()) &&
            !(this.getName().equals(trainee.getName())) &&
            !(this.getGender().equals(trainee.getGender()))) {
            return false;
        }
        return true;
    } 

    /**
     * <p>
     * override the hashCode method. to generate the hashcode for the trainee object as id * 32.
     * </p>
     *
     * @return {@link int} return hashcode of all object as 0;
     **/    

    @Override
    public int hashCode() {
        return getId() * 32;
    }  

}