package com.ideas2it.employee.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import com.ideas2it.employee.model.Trainer;
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
    @Override
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
                    .append("\n\tb) Age must above 18.\n");
            validationErrorList.add(2);
        }
        String mobileNumber = Long.toString(trainer.getEmployee().getMobileNumber());
        if (!StringUtil.isValidMobileNumber(mobileNumber)) {
            errorMessage.append(slNo++).append(". Invalid Mobile Number. It must contain 10 digits\n");
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

   /**
     * <p>
     * Return Trainer List.
     * </p>
     * 
     * @return {@link List<Trainer>} return List of Trainers.
     **/
    @Override
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
    @Override
    public boolean removeTrainerById(int id) {
        return dao.deleteTrainerById(id);
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
    @Override
    public Trainer getTrainerById(int id) {
        return dao.retrieveTrainerById(id);
    }
}