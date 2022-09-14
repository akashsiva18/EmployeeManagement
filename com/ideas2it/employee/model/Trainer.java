package com.ideas2it.employee.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.util.DateUtil;

/**
 * <h2>Trainer</h2>
 * <p>
 * It is a model class for trainer conatins details of trainer
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public class Trainer {		

    private int experience;
    private int noOfTrainees;
    private int trainerId;
    private Employee employee;
    private Set<Trainee> trainees;

    /**
     * <p>
     * It is a constructor for Trainer class.
     * </p>
     *
     * @param {@link Employee} employee 
     *               - it is an object of employee that contains common details of employee
     * @param {@link int} experiance - experiance of trainer
     *
     * @return {@link void} return nothing.
     **/
    public Trainer(Employee employee, int experience) {
        this.employee = employee;
        this.experience = experience;
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

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getTrainerId() {
	return trainerId;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
	return employee;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
   }

    public Set<Trainee> getTrainees() {
        return trainees;
    }

    /**
     * <p>
     * overrided toString method
     * </p>
     *
     * @return {@link String} return formated string.
     **/
    @Override
    public String toString() {
        int size = trainees.size();
        int age = DateUtil.computeYears(getEmployee().getDateOfBirth(),LocalDate.now());
	return "\nID of Employee : " + employee.getId() + "\nName of the Trainer : " 
               + employee.getName() + "\nAge : " + age + "\nGender : " + employee.getGender() 
               + "\nQualifications : " + employee.getQualification() +"\nAddress : " 
               + employee.getAddress() + "\nMobile Number : " + employee.getMobileNumber() 
               + "\nEmail Id : " + employee.getEmailId() + "\nDate Of Joining : " 
               + employee.getDateOfJoining().format(DateTimeFormatter.ofPattern("d/MM/yyyy")) 
               + "\nTraining Experience : " + getExperience() + "\nNo Of Trainees : " + size;
    }


    /**
     * <p>
     * overrided the equals method. To check the required field.
     * </p>
     *
     * @param {@link Object) o.
     *
     * @return {@link boolean} return true if all the condition satisfied else false.
     **/
    @Override
    public boolean equals(Object o) {
        Trainer trainer = (Trainer)o;
        if (!(this.getEmployee().getId() == trainer.getEmployee().getId()) &&
            !(this.getEmployee().getName().equals(trainer.getEmployee().getName())) &&
            !(this.getEmployee().getGender().equals(trainer.getEmployee().getGender()))) {
            return false;
        }
        return true;
    }

    /**
     * <p>
     * overrided the hashCode method. to generate the hashcode for the trainee object as ID * 32.
     * </p>
     *
     * @return {@link int} return hashcode of all object as 0;
     **/    
    @Override
    public int hashCode() {
        return getEmployee().getId() * 32;
    }

}

    
