package com.ideas2it.employee.service.intf;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;

import java.util.List;
import java.util.ArrayList;

/**
 * <h2>TraineeServiceImpl</h2>
 * <p>
 * It done insert, retrive, delete, update data in the database.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public interface TraineeServiceIntf {

    /**
     * <p>
     * It valid the details if no error found create object and sent to Dao for add in DataBase
     * else add errors to list. Return List of errors.
     * </p>
     *
     * @param {@link Trainee} trainee - Object of the Trainee.
     *
     * @return {@link List<Integer>} return List of errors.
     * @throws BadRequest
     * @throws EmployeeNotFound
     **/
    public List<Integer> validateAndAddOrUpdateTraineeDetails(Trainee trainee) throws BadRequest, EmployeeNotFound;

    /**
     * <p>
     * Return Trainee List.
     * </p>
     *
     * @return {@link List<Trainee>} return List of Trainees.
     **/
    public List<Trainee> getTrainees();

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
    public boolean removeTraineeById(int id) throws EmployeeNotFound;

    /**
     * <p>
     * return trainee if id is matched else throw exception.
     * </p>
     * 
     * @param {@link int} id - trainee Object that contails all the details.
     *
     * @return {@link Trainee}.
     * @throws EmployeeNotFound
     **/
    public Trainee getTraineeById(int id) throws EmployeeNotFound;       
}