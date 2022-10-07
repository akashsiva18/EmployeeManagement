package com.ideas2it.employee.service.impl;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.dao.intf.TrainerDaoIntf;
import com.ideas2it.employee.service.intf.TrainerServiceIntf;
import com.ideas2it.employee.util.DateUtil;
import com.ideas2it.employee.util.StringUtil;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <h2>TrainerServiceImpl</h2>
 * <p>
 * It done insert, retrieve, delete, update data in the DataBase.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
@Service
public class TrainerServiceImpl implements TrainerServiceIntf {
    @Autowired
    private TrainerDaoIntf dao;

    /**
     * <p>
     * It valid the details if no error found create object and sent to Dao for add in database
     * else add errors to list. Return List of errors.
     * </p>
     *
     * @param {@link Trainer} trainer - Object of the trainer
     * 
     * @return {@link List<Integer>} return List of errors.
     * @throws BadRequest
     *
     **/

    public List<Integer> validateAndAddOrUpdateTrainerDetails(Trainer trainer) throws BadRequest {
        List<Integer> validationErrorList = new ArrayList<>();
        Integer slNo = 1;
        StringBuilder errorMessage = new StringBuilder("\nValidation Errors\n");
        if (!StringUtil.isValidName(trainer.getEmployee().getName())) {
            errorMessage.append(slNo++).append(". Invalid Name. It must contain Alphabet.\n");
            validationErrorList.add(1);
        }
        LocalDate validDateOfBirth = LocalDate.now();
        Integer age = 0;
        if (!DateUtil.isAgeEligible(trainer.getEmployee().getDateOfBirth(),18)) {
            errorMessage.append(slNo++).append(". Invalid Date Of Birth.\n\ta)")
                    .append(" Date must in DD/MM/YYYY format.")
                    .append("\n\tb) Age must above 18.\n");
            validationErrorList.add(2);
        }
        String mobileNumber = Long.toString(trainer.getEmployee().getMobileNumber());
        if (!StringUtil.isValidMobileNumber(mobileNumber)) {
            errorMessage.append(slNo++).append(". Invalid Mobile Number. It must contain only Numbers\n");
            validationErrorList.add(3);
        }
        if (!StringUtil.isEmailValid(trainer.getEmployee().getEmailId())) {
            errorMessage.append(slNo++).append(". Invalid Mail Id.\n");
            validationErrorList.add(4);
        }
        if (DateUtil.isFutureDate(trainer.getEmployee().getDateOfJoining())) {
                errorMessage.append(slNo++).append(". Date of joining must not be a future Date.\n");
                validationErrorList.add(5);
        }
        Role role = new Role();
        role.setDescription("Trainer");
        trainer.getEmployee().setRole(role);
        if (validationErrorList.isEmpty()) {
            dao.insertOrUpdateTrainer(trainer);
        } else {
            throw new BadRequest(errorMessage.toString(), validationErrorList);
        }
        return validationErrorList;
    }
/*
    public List<Integer> validateAndAddOrUpdateTrainerDetails(final String name,
            final String dateOfBirth, final String gender, final String qualification,
            final String address, final String mobileNumber, final String emailId, 
            final String dateOfJoining, Trainer oldTrainer) throws BadRequest {
        List<Integer> validationErrorList = new ArrayList<>();
        Integer slNo = 1;
        StringBuilder errorMessage = new StringBuilder("\nValidation Errors\n");
        if (!StringUtil.isValidName(name)) {
            errorMessage.append(slNo++ + ". Invalid Name. It must contain Alphabet.\n");
            validationErrorList.add(1);
        }
        LocalDate validDateOfBirth = LocalDate.now();
        Integer age = 0;
        if (DateUtil.isValidDateOfBirth(dateOfBirth, 18)) {
            validDateOfBirth = DateUtil.parseStringToDate(dateOfBirth);
        } else {
            errorMessage.append(slNo++ + ". Invalid Date Of Birth.\n\ta)" 
                + " Date must in DD/MM/YYYY format." 
                + "\n\tb) Age must above 18.\n");
            validationErrorList.add(2);
        } 
        Long validMobileNumber = 0l;
        if (StringUtil.isValidMobileNumber(mobileNumber)) {
            validMobileNumber =  Long.parseLong(mobileNumber);
        } else {
            errorMessage.append(slNo++ + ". Invalid Mobile Number. It must contain only Numbers\n");
            validationErrorList.add(3);
        }
        String validEmailId = null;
        if (StringUtil.isEmailValid(emailId)) {
            validEmailId = emailId;
        } else {
            errorMessage.append(slNo++ + ". Invalid Mail Id.\n");
            validationErrorList.add(4);
        }
        LocalDate validDateOfJoining = LocalDate.now();
        int trainingExperience = 0;
        if (DateUtil.isValidDateFormat(dateOfJoining)) {
            validDateOfJoining = DateUtil.parseStringToDate(dateOfJoining);
            trainingExperience = DateUtil.computeYears(
                                 validDateOfJoining,LocalDate.now());
            if (DateUtil.isFutureDate(validDateOfJoining)) {
                errorMessage.append(slNo++ + ". Date of joining must not be a future Date.\n");
                validationErrorList.add(5);
            }
        } else {
            errorMessage.append(slNo++ + ". Invalid Date Of Joining.\n");
            validationErrorList.add(5);
        }  

        Qualification qualificationDetails = new Qualification();
        qualificationDetails.setCourse(qualification);
        Role role = new Role();
        role.setDescription("Trainer");
        if (validationErrorList.isEmpty()) {
            if (null == oldTrainer) {
                Employee employee = new Employee(name, validDateOfBirth, gender, 
                                    qualificationDetails, address, validMobileNumber, 
                                    validEmailId, validDateOfJoining, role);
                Trainer trainer = new Trainer(employee);
                dao.insertOrUpdateTrainer(trainer);
            } else {
                oldTrainer.getEmployee().setName(name);
                oldTrainer.getEmployee().setDateOfBirth(validDateOfBirth);
                oldTrainer.getEmployee().setGender(gender);
                oldTrainer.getEmployee().setQualification(qualificationDetails);
                oldTrainer.getEmployee().setAddress(address);
                oldTrainer.getEmployee().setMobileNumber(validMobileNumber);
                oldTrainer.getEmployee().setEmailId(validEmailId);
                oldTrainer.getEmployee().setDateOfJoining(validDateOfJoining);
                dao.insertOrUpdateTrainer(oldTrainer);
            }
        } else {
            throw new BadRequest(errorMessage.toString(), validationErrorList);
        }
        return validationErrorList;
    }
*/

   /**
     * <p>
     * Return Trainer List.
     * </p>
     * 
     * @return {@link List<Trainer>} return List of Trainers.
     **/
    public List<Trainer> getTrainers() {
        return dao.retrieveTrainers();
    } 

    /**
     * <p>
     * Remove Trainer by using Id of the trainer. 
     * </p>
     * 
     * @param {@link String} id.
     *
     * @return {@link boolean} - if deleted return true else false
     * @throws EmployeeNotFound
     **/
    public boolean removeTrainerById(int id) throws EmployeeNotFound {
        boolean isTrainerDeleted = dao.deleteTrainerById(id);
        if (!isTrainerDeleted) {
             throw new EmployeeNotFound("Trainer Id " + id + " Not Found");
        }
        return isTrainerDeleted;
    }  

    /**
     * <p>
     * Return trainer if id is matched else throws Exception.
     * </p>
     * 
     * @param {@link int} id - id of the Trainer.
     *
     * @return {@link Trainer}
     *         - that contain a copy of the Trainer matches to the id.
     * @throws EmployeeNotFound
     **/
    public Trainer getTrainerById(int id) throws EmployeeNotFound { 
        Trainer oldTrainer = dao.retrieveTrainerById(id);
        if (oldTrainer == null) {
           throw new EmployeeNotFound("Trainer Id " +id + " Not Found.");
        }  
        return oldTrainer;
    }
}