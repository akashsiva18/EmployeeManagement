package com.ideas2it.employee.service;

import com.ideas2it.employee.DTO.TraineeDTO;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.exception.BadRequest;

import java.util.List;

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
public interface TraineeService {

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
     **/
    public List<Integer> validateAndAddOrUpdateTraineeDetails(TraineeDTO traineeDTO) throws BadRequest;

    /**
     * <p>
     * Return Trainee List.
     * </p>
     *
     * @return {@link List<Trainee>} return List of Trainees.
     **/
    public List<TraineeDTO> getTrainees();

    /**
     * <p>
     * Remove Trainee by using Id of the trainee if no id not found it throws exception.
     * </p>
     *
     * @param {@link int} id.
     **/
    public void removeTraineeById(int id);

    /**
     * <p>
     * return trainee if id is matched else throw exception.
     * </p>
     * 
     * @param {@link int} id - trainee Object that contails all the details.
     *
     * @return {@link Trainee}.
     **/
    public TraineeDTO getTraineeById(int id);
}