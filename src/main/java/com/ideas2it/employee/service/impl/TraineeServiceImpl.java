package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.dao.impl.TraineeDaoImpl;
import com.ideas2it.employee.dao.intf.TraineeDaoIntf;
import com.ideas2it.employee.service.intf.TraineeServiceIntf;
import com.ideas2it.employee.util.DateUtil;
import com.ideas2it.employee.util.StringUtil;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.intf.TrainerServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.lang.NumberFormatException;

/**
 * <h2>TraineeServiceImpl</h2>
 * <p>
 * It done insert, retrieve, delete, update data in the database.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
@Service
public class TraineeServiceImpl implements TraineeServiceIntf{
    @Autowired
    private TraineeDaoIntf dao;
    @Autowired
    private TrainerServiceIntf trainerService;

    /**
     * <p>
     * It valid the details if no error found create object and sent to Dao for add in DataBase
     * else add errors to list. Return List of errors.
     * </p>
     *
     * @param {@link String} name - name of the trainee.
     * @param {@link String} dateOfBirth = data Of Birth of the trainee.
     * @param {@link String} gender - gender of the trainee.
     * @param {@link Qualification} qualification - qualification of trainee.
     * @param {@link String} address - address of the employee.
     * @param {@link String} mobileNumber - mobileNumber of the trainee.
     * @param {@link String} emailId - email of the trainee.
     * @param {@link String} dateOfJoining - date of joining of the trainee.
     * @param {@link List<String>} trainerIdAsList 
                           - trainer Names who are in charge of the trainee.
     * @param {@link String} trainingPeriodInMonths 
                           - training period of the trainee.
     *   
     * @return {@link List<Integer>} return List of errors.
     * @throws BadRequest
     * @throws EmployeeNotFound
     **/
    public List<Integer> validateAndAddOrUpdateTraineeDetails(Trainee trainee) throws BadRequest, EmployeeNotFound {
        
        List<Trainer> trainerAsList = trainerService.getTrainers();
        List<Integer> validationErrorList = new ArrayList<>();
        List<Integer> validTrainerId = new ArrayList<>();
        List<Integer> invalidTrainerId = new ArrayList<>();
        Set<Trainer> trainersOfTheTrainee = new HashSet<>();
        Integer slNo = 1;
        StringBuilder errorMessage = new StringBuilder("\nValidation Errors\n");
        if (!StringUtil.isValidName(trainee.getEmployee().getName())) {
            errorMessage.append(slNo++).append(". Invalid Name. It must contain Alphabet.\n");
            validationErrorList.add(1);
        }
        LocalDate validDateOfBirth = LocalDate.now();
        Integer age = 0;
        if (!DateUtil.isAgeEligible(trainee.getEmployee().getDateOfBirth(),18)) {
            errorMessage.append(slNo++).append(". Invalid Date Of Birth.\n\ta)")
                    .append(" Date must in DD/MM/YYYY format.")
                    .append("\n\tb) Age must above 18.\n");
            validationErrorList.add(2);
        }
        String mobileNumber = Long.toString(trainee.getEmployee().getMobileNumber());
        if (!StringUtil.isValidMobileNumber(mobileNumber)) {
            errorMessage.append(slNo++).append(". Invalid Mobile Number. It must contain only Numbers\n");
            validationErrorList.add(3);
        }
        if (!StringUtil.isEmailValid(trainee.getEmployee().getEmailId())) {
            errorMessage.append(slNo++).append(". Invalid Mail Id.\n");
            validationErrorList.add(4);
        }
        if (DateUtil.isFutureDate(trainee.getEmployee().getDateOfJoining())) {
            errorMessage.append(slNo++).append(". Date of joining must not be a future Date.\n");
            validationErrorList.add(5);
        }

        for (String trainerId : trainerIdAsList) {
            try {
                int validId = Integer.parseInt(trainerId);
                for (int i = 0; i < trainerAsList.size(); i++) {
                    if (trainerAsList.get(i).getEmployee().getId() == validId) {
                        trainersOfTheTrainee.add(trainerAsList.get(i));
                        validTrainerId.add(validId);
                        break;
                    } else {
                        if (i == trainerAsList.size() - 1) {
                            invalidTrainerId.add(validId);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                errorMessage.append(slNo++ + ". Invalid Trainer Id.\n");
                validationErrorList.add(6);
                break;
            }
        }
        int validTrainingPeriod = 0;
        try {
            validTrainingPeriod = Integer.parseInt(trainingPeriodInMonths);
        } catch (NumberFormatException e) {
            errorMessage.append(slNo++ + ". The training period must be in Numerical value.\n");
            validationErrorList.add(7);
        }
        Role role = new Role();
        role.setDescription("Trainee");
        if (validationErrorList.isEmpty()) {
                dao.insertOrUpdateTrainee(trainee);
        } else {
            throw new BadRequest(errorMessage.toString(), validationErrorList);
        }
        if (!invalidTrainerId.isEmpty()) {
            throw new EmployeeNotFound("Invalid Trainer Id.\n"
                        + invalidTrainerId.toString().replaceAll("[\\[\\]]",""));
        }
        return validationErrorList;
    }

    /**
     * <p>
     * Return Trainee List.
     * </p>
     * 
     * @return {@link List<Trainee>} return List of Trainees.
     **/
    public List<Trainee> getTrainees() {
        return dao.retrieveTrainees();
    }  

    /**
     * <p>
     * Remove Trainee by using Id of the trainee if no id not found it throws exception.
     * </p>
     * 
     * @param {@link int} id.
     *
     * @return {@link boolean} - if deleted return true else false
     * @throws EmployeeNotFound
     **/
    public boolean removeTraineeById(int id) throws EmployeeNotFound { 
        boolean isTraineeDeleted = dao.deleteTraineeById(id);
        if (!isTraineeDeleted) {
            throw new EmployeeNotFound("Trainee Id " + id + " Not Found");
        }
        return isTraineeDeleted;
    }

    /**
     * <p>
     * return trainee if id is matched else throw exception.
     * </p>
     * 
     * @param {@link int} id - trainee Object that contains all the details.
     *
     * @return {@link Trainee}.
     * @throws EmployeeNotFound
     **/
    public Trainee getTraineeById(int id) throws EmployeeNotFound {
        Trainee oldTrainee = dao.retrieveTraineeById(id);
        if (oldTrainee == null) {
            throw new EmployeeNotFound("Trainee Id " + id + " Not Found.");
        }  
        return oldTrainee;
    }
}