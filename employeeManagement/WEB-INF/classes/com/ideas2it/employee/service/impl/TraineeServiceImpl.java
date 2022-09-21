package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.model.Trainee;
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

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
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
public class TraineeServiceImpl implements TraineeServiceIntf{  
    private TraineeDaoIntf dao = new TraineeDaoImpl();

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
    public List<Integer> validateAndAddTraineeDetails(final String name, 
            final String dateOfBirth, final String gender, final String qualification,
            final String address, final String mobileNumber, final String emailId, 
            final String dateOfJoining, final List<String> trainerIdAsList,
            final String trainingPeriodInMonths) throws BadRequest, EmployeeNotFound {
        List<Integer> validationErrorList = new ArrayList<>();
        List<Integer> validTrainerId = new ArrayList<>();
        Integer slNo = 1;
        StringBuilder errorMessage = new StringBuilder("\nValidation Errors\n");
        if (!StringUtil.isValidName(name)) {
            errorMessage.append(slNo++ + ". Invalid Name. It must contain Alphabet.\n");
            validationErrorList.add(1);
        }
        LocalDate validDateOfBirth = LocalDate.now();
        Integer age = 0;
        if (DateUtil.isValidDateOfBirth(dateOfBirth,18)) {
            validDateOfBirth = DateUtil.parseStringToDate(dateOfBirth);
            age = DateUtil.computeYears(validDateOfBirth,LocalDate.now());
        } else {
            errorMessage.append(slNo++ + ". Invalid Date Of Birth.\n\ta) " 
                                + "Age must above 18.\n");   
            validationErrorList.add(2);
        }

        Long validMobileNumber = 0l;
        if (StringUtil.isValidMobileNumber(mobileNumber)) {
            validMobileNumber =  Long.parseLong(mobileNumber);
        } else {
            errorMessage.append(slNo++ + ". Invalid Mobile Number."
                                + " It must contain only Numbers\n");
            validationErrorList.add(3);
        }

        String validEmailId = "";
        if (StringUtil.isEmailValid(emailId)) {
            validEmailId = emailId;
        } else {
            errorMessage.append(slNo++ + ". Invalid Mail Id.\n");
            validationErrorList.add(4);
        }
        LocalDate validDateOfJoining = LocalDate.now();
        if (DateUtil.isValidDateFormat(dateOfJoining)) {
            validDateOfJoining = DateUtil.parseStringToDate(dateOfJoining);
            if (DateUtil.isFutureDate(validDateOfJoining)) {
                errorMessage.append(slNo++ + ". The date of joining must not be a future Date.\n");
                validationErrorList.add(5);
            }
        } else {
            errorMessage.append(slNo++ + ". Invalid Date Of Joining.\n");
            validationErrorList.add(5);
        }

        for (String trainerId : trainerIdAsList) {
            try {
                int validId = Integer.parseInt(trainerId);
                validTrainerId.add(validId);
            } catch (NumberFormatException e) {
                errorMessage.append(slNo++ + ". Invalid Trainer Id. Id must contain Numbers\n");
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
        Qualification qualificationDetails = new Qualification();
        qualificationDetails.setCourse(qualification);
        Role role = new Role();
        role.setDescription("Trainee");
        if (validationErrorList.isEmpty()) {
            Employee employee = new Employee(name, validDateOfBirth, gender, qualificationDetails,
                                address, validMobileNumber, validEmailId, 
                                validDateOfJoining, role);
            Trainee trainee = new Trainee(employee, validTrainingPeriod, validTrainerId);
            dao.insertTrainee(trainee);
        } else {
            throw new BadRequest(errorMessage.toString(), validationErrorList);
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
       
    /**
     * <p>
     * It validate the details if no error found update the details in the object and sent to dao.
     * else add errors to list. Return List of errors.
     * </p>
     *
     * @param {@link String} qualification - qualification of the trainee.
     * @param {@link String} address - address of the trainee.	
     * @param {@link String} mobileNumber - mobile number of the trainee.
     * @param {@link List<String>} trainerNamesAsList 
     *                     - trainer Names who are in charge of the trainee.
     * @param {@link Trainee} trainee - this is the object we're going to update all the detail.
     *
     * @return {@link List<Integer>} return List of errors.
     * @throws BadRequest
     * @throws EmployeeNotFound
     **/
    public List<Integer> updateAllDetailsOfTraineeById(final String qualification,
            final String address, final String mobileNumber, final List<String> trainersIdAsList,
            final Trainee trainee) throws BadRequest, EmployeeNotFound {
        List<Integer> validationErrorList = new ArrayList<>();
        List<Integer> validTrainersId = new ArrayList<>();
        int slNo = 1;
        StringBuilder errorMessage = new StringBuilder("\nValidation Errors\n");
        if (qualification != "") {
            trainee.getEmployee().getQualification().setCourse(qualification);
        }
        if (address != "") {
            trainee.getEmployee().setAddress(address);
        }
        Long validMobileNumber = 0l;
        if (mobileNumber != "") {
            if (StringUtil.isValidMobileNumber(mobileNumber)) {
                validMobileNumber = Long.parseLong(mobileNumber);
                trainee.getEmployee().setMobileNumber(validMobileNumber);
            } else {
                errorMessage.append(slNo++ + ". Invalid Mobile Number. It must contains only Numbers\n");
                validationErrorList.add(1);
            }
        }
        if (!trainersIdAsList.isEmpty()) {
            for (int i = 0; i < trainersIdAsList.size(); i++) {
                if (!StringUtil.isValidId(trainersIdAsList.get(i))) {
                    errorMessage.append(slNo++ + ". Invalid Trainer Id. Id must contain Numbers\n");
                    validationErrorList.add(2);
                    break;
                } else {
                    validTrainersId.add(Integer.parseInt(trainersIdAsList.get(i)));
                }  
            }
            trainee.setTrainersId(validTrainersId);
        }
        if (!validationErrorList.isEmpty()) {
            errorMessage.append("Please re-enter the valid information.\n");
            throw new BadRequest(errorMessage.toString(), validationErrorList);
        } else {
            dao.updateTraineeDetails(trainee);
        }
        return validationErrorList;   
    } 
}