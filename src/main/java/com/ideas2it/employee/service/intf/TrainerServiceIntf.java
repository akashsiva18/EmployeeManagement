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
     * @param {@link Trainer} trainer - Object of the trainer
     *
     * @return {@link List<Integer>} return List of errors.
     * @throws BadRequest
     *
     **/
    public List<Integer> validateAndAddOrUpdateTrainerDetails(Trainer trainer) throws BadRequest;

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
     **/
    public boolean removeTrainerById(int id);

    /**
     * <p>
     * Return trainer by id.
     * </p>
     * 
     * @param {@link int} id - id of the Trainer.
     *
     * @return {@link Trainer}
     *         - that contain a copy of the Trainer matches to the id.
     **/
    public Trainer getTrainerById(int id);

    /**
     * It returns the trainers object as list.
     *
     * @param trainerIds - list of Trainers Ids.
     * @return List<Trainers> - it contains the trainers.
     *
     **/
    public List<Trainer> getMultipleTrainerByIds(List<Integer> trainerIds);
}