package com.ideas2it.employee.DTO;

import com.ideas2it.employee.util.DateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * <h2>TrainerDTO</h2>
 * <p>
 * It is a model class for trainer contains details of trainer
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public class TrainerDTO extends EmployeeDTO {

    private int experience;
    private int noOfTrainees;

    /**
     * <p>
     * It is a constructor for TrainerDTO class.
     * </p>
     *
     * @param {@link EmployeeDTO} employee
     *               - it is an object of employee that contains common details of employee
     * @param {@link int} experience - experience of trainer
     *
     **/
    public TrainerDTO(EmployeeDTO employee) {
        this.experience = DateUtil.computeYears(employee.getDateOfJoining(),LocalDate.now());
    }

    public TrainerDTO(int id, String name, LocalDate dateOfBirth, String gender, QualificationDTO qualificationDTO,
                      String address, long mobileNumber, String emailId, LocalDate dateOfJoining, RoleDTO roleDTO,
                      int experience, int noOfTrainees, Set<TraineeDTO> trainees) {
        super(id, name, dateOfBirth, gender, qualificationDTO, address, mobileNumber, emailId, dateOfJoining, roleDTO);
        this.experience = experience;
        this.noOfTrainees = noOfTrainees;
    }

    /**
     * <p>
     * It is a default constructor.
     * </p>
     *
     **/
    public TrainerDTO(){
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

    /**
     * <p>
     * override toString method
     * </p>
     *
     * @return {@link String} return formatted string.
     **/
    @Override
    public String toString() {
        int age = DateUtil.computeYears(getDateOfBirth(),LocalDate.now());
	return "\nID of EmployeeDTO : " + getId() + "\nName of the TrainerDTO : "
               + getName() + "\nAge : " + age + "\nGender : " + getGender()
               + "\nQualifications : " + getQualificationDTO() +"\nAddress : "
               + getAddress() + "\nMobile Number : " + getMobileNumber()
               + "\nEmail Id : " + getEmailId() + "\nDate Of Joining : "
               + getDateOfJoining().format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
               + "\nTraining Experience : " + getExperience() + "\nNo Of Trainees : " + noOfTrainees;
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

    
