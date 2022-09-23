package com.ideas2it.employee.service.intf;

import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;

import java.util.List;
import java.util.ArrayList;

/**
 * <h2>TrainerServiceIntf</h2>
 * <p>
 * It is an Interface of Trainer Service.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public interface TrainerServiceIntf {

    /**
     * <p>
     * It valid the details if no error found create object and sent to Dao for add in database
     * else add errors to list. Return List of errors.
     * </p>
     *
     * @param {@link String} name - name of trainer.
     * @param {@link String} dateOfBirth - data of birth of trainer.
     * @param {@link String} gender - gender of trainer.
     * @param {@link String} qualification - qualification of trainer.
     * @param {@link String} address - address of trainer.
     * @param {@link String} mobileNumber - mobileNumber of trainer.
     * @param {@link String} emailId - email of trainer.
     * @param {@link String} dateOfJoining - date of joining of trainer.
     * 
     * @return {@link List<Integer>} return List of errors.
     * @throws BadRequest.
     **/
    public List<Integer> validateAndAddTrainerDetails(final String name, final String dateOfBirth,
                                  final String gender, final String qualification, final String address, 
                                  final String mobileNumber, final String emailId, 
                                  final String dateOfJoining, Trainer oldTrainer) throws BadRequest;

   /**
     * <p>
     * Return Trainer List.
     * </p>
     * 
     * @return {@link List<Trainer>} return List of Trainers.
     **/
    public List<Trainer> getTrainers();

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
    public boolean removeTrainerById(int id) throws EmployeeNotFound;

    /**
     * <p>
     * Return trainer if id is matched else throws Exception.
     * </p>
     * 
     * @param {@link int} id - id of the Trainer.
     *
     * @return {@link Trainee} 
     *         - that contain a copy of the Trainer matches to the id.
     * @throws EmployeeNotFound
     **/
    public Trainer getTrainerById(int id) throws EmployeeNotFound;    
}