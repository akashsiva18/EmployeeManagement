package com.ideas2it.employee.DTO;

import com.ideas2it.employee.util.DateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>TraineeDTO</h2>
 * <p>
 * It is a model class for trainee contains details of trainee
 * and contains getter to access the variables and setter to set the values in the variable.
 * </p>
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/

public class TraineeDTO extends EmployeeDTO {
    private int trainingPeriod;
    private List<Integer> trainerIds;
    private String trainersName;

    public String getTrainersName() {
        return trainersName;
    }

    public void setTrainersName(String trainersName) {
        this.trainersName = trainersName;
    }

    public void setTrainingPeriod(int trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    public int getTrainingPeriod() {
	return trainingPeriod;
    }


    public List<Integer> getTrainerIds() {
        return trainerIds;
    }

    public void setTrainerIds(List<Integer> trainerIds) {
        this.trainerIds = trainerIds;
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
        trainerIds = new ArrayList<>();
        int age = DateUtil.computeYears(getDateOfBirth(),LocalDate.now());
	return  "\n" + "ID of EmployeeDTO : " + getId() +"\nName of the TraineeDTO : "
                + getName() + "\nAge : " + age +"\nGender : "
                + getGender() + "\nQualifications : " + getQualificationDTO()
                + "\nAddress : " + getAddress() + "\nMobile Number : "
                + getMobileNumber() + "\nEmail Id : " + getEmailId()
                + "\nDate Of Joining : " + getDateOfJoining().format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                + "\nTraining Period : " + getTrainingPeriod() + "\nTrainers : " 
                + trainerIds.toString().replaceAll("[\\[\\]]","");
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
        TraineeDTO trainee = (TraineeDTO)o;
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