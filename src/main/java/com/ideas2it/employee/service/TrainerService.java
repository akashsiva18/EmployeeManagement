package com.ideas2it.employee.service;

import com.ideas2it.employee.DTO.TrainerDTO;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.exception.BadRequest;

import java.util.List;

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
public interface TrainerService {

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
    public List<Integer> validateAndAddOrUpdateTrainerDetails(TrainerDTO trainerDTO) throws BadRequest;

   /**
     * <p>
     * Return Trainer List.
     * </p>
     * 
     * @return {@link List<Trainer>} return List of Trainers.
     **/
    public List<TrainerDTO> getTrainers();

    /**
     * <p>
     * Remove Trainer by using Id of the trainer.
     * </p>
     *
     * @param {@link String} id.
     **/
    public void removeTrainerById(int id);

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
    public TrainerDTO getTrainerById(int id);

    /**
     * It returns the trainers object as list.
     *
     * @param trainerIds - list of Trainers Ids.
     * @return List<Trainers> - it contains the trainers.
     *
     **/
    public List<Trainer> getMultipleTrainerByIds(List<Integer> trainerIds);
}