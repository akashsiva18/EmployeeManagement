package com.ideas2it.employee.dao.intf;

import java.util.List;
import com.ideas2it.employee.model.Trainer;

/**
 * <h2>TrainerDaoIntf</h2>
 * <p>
 * It done insert, retrive, delete, update data in the Database.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public interface TrainerDaoIntf {

    /**
     * <p>
     * Insert trainer in the database.
     * </p>
     * 
     * @param {@link Trainer} trainer - trainer Object that contails all the details.
     *
     * @return {@link void} return nothing 
     **/
    public void insertOrUpdateTrainer(Trainer trainer);

    /**
     * <p>
     * Return Trainer List.
     * </p>
     * 
     * @return {@link List<Trainer>} return List of Trainers.
     **/
    public List<Trainer> retrieveTrainers();

    /**
     * <p>
     * Delete Trainer by using Id of the trainer.
     * </p>
     * 
     * @param {@link int} id - used to check the trainer id is available.
     *
     * @return {@link boolean} - if deleted return true else false.
     **/
    public boolean deleteTrainerById(int id);

    /**
     * <p>
     * return new trainer object that conations the old information in the given id of the trainer.
     * </p>
     * 
     * @param {@link String} id - used to check the trainer is available.
     *
     * @return {@link Trainer} if id exist return new trainer object else null.
     **/
    public Trainer retrieveTrainerById(int id);
}