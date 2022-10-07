package com.ideas2it.employee.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import com.ideas2it.employee.util.DateUtil;

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
public class Trainee {
    private int trainingPeriod;
    private int traineeId;
    private Employee employee;
    private List<Integer> trainersId;
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
    public Trainee(Employee employee, int trainingPeriod , List<Integer> trainersId) {
        this.employee = employee;
        this.trainingPeriod = trainingPeriod;
        this.trainersId = trainersId;
    }


    /**
     * <p>
     * It is a default constructor.
     * </p>
     *
     **/
    public Trainee(){
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    public int getTraineeId() {
	return traineeId;
    }

    public void setTrainingPeriod(int trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    public int getTrainingPeriod() {
	return trainingPeriod;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
	return employee;
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
            trainersId.add(trainer.getEmployee().getId());
        }
        int age = DateUtil.computeYears(getEmployee().getDateOfBirth(),LocalDate.now());
	return  "\n" + "ID of Employee : " + employee.getId() +"\nName of the Trainee : " 
                + employee.getName() + "\nAge : " + age +"\nGender : " 
                + employee.getGender() + "\nQualifications : " + employee.getQualification() 
                + "\nAddress : " + employee.getAddress() + "\nMobile Number : "
                + employee.getMobileNumber() + "\nEmail Id : " + employee.getEmailId() 
                + "\nDate Of Joining : " + employee.getDateOfJoining().format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
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
        if (!(this.getEmployee().getId() == trainee.getEmployee().getId()) &&
            !(this.getEmployee().getName().equals(trainee.getEmployee().getName())) &&
            !(this.getEmployee().getGender().equals(trainee.getEmployee().getGender()))) {
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
        return getEmployee().getId() * 32;
    }  
}