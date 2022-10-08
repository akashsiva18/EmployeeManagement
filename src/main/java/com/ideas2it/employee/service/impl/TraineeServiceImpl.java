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
     * @param {@link Trainee} trainee - object of the trainee.
     *
     * @return {@link List<Integer>} return List of errors.
     * @throws BadRequest
     **/
    @Override
    public List<Integer> validateAndAddOrUpdateTraineeDetails(Trainee trainee) throws BadRequest {

        List<Integer> validationErrorList = new ArrayList<>();
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
            errorMessage.append(slNo++).append(". Invalid Date Of Birth.\n\t")
                    .append("\n\tb) Age must above 18.\n");
            validationErrorList.add(2);
        }
        String mobileNumber = Long.toString(trainee.getEmployee().getMobileNumber());
        if (!StringUtil.isValidMobileNumber(mobileNumber)) {
            errorMessage.append(slNo++).append(". Invalid Mobile Number. It must contain 10 digits\n");
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
        for (int trainerId : trainee.getTrainersId()) {
            trainersOfTheTrainee.add(trainerService.getTrainerById(trainerId));
        }
        trainee.setTrainers(trainersOfTheTrainee);
        Role role = new Role();
        role.setDescription("Trainee");
        trainee.getEmployee().setRole(role);
        if (validationErrorList.isEmpty()) {
                dao.insertOrUpdateTrainee(trainee);
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
    @Override
    public List<Trainee> getTrainees() {
        return dao.retrieveTrainees();
    }  

    /**
     * <p>
     * Remove Trainee by using Id.
     * </p>
     * 
     * @param {@link int} id.
     *
     * @return {@link boolean} - if deleted return true else false
     **/
    @Override
    public boolean removeTraineeById(int id) {
        return dao.deleteTraineeById(id);
    }

    /**
     * <p>
     * return trainee by using the id
     * </p>
     * 
     * @param {@link int} id - trainee Object that contains all the details.
     *
     * @return {@link Trainee}.
     **/
    @Override
    public Trainee getTraineeById(int id) throws EmployeeNotFound {
        Trainee oldTrainee = dao.retrieveTraineeById(id);
        List<Integer> trainerIds = new ArrayList<>();
        for (Trainer trainer : oldTrainee.getTrainers()) {
            trainerIds.add(trainer.getEmployee().getId());
        }
        oldTrainee.setTrainersId(trainerIds);
        return oldTrainee;
    }
}