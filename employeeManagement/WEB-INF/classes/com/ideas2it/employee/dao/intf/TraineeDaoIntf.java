package com.ideas2it.employee.dao.intf;

import java.util.List;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.exception.EmployeeNotFound;

/**
 * <h2>TraineeDaoIntf</h2>
 * <p>
 * It done insert, retrive, delete, update data in the Database.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public interface TraineeDaoIntf {

    /**
     * <p>
     * Insert trainee in the Database
     * </p>
     * 
     * @param {@link Trainee} trainee - trainee Object that contails all the details.
     *
     * @return {@link void} return nothing 
     * @throws EmployeeNotFound
     **/
    public void insertTrainee(Trainee trainee) throws EmployeeNotFound;

    /**
     * <p>
     * Get data from Database and return as List of Trainees.
     * </p>
     * 
     * @return {@link List<Trainee>} return List of Trainees.
     **/
    public List<Trainee> retrieveTrainees();

    /**
     * <p>
     * Delete Trainee by using Id of the trainee in the Database. 
     * </p>
     * 
     * @param {@link int} id - used to check the trainee id is available.
     *
     * @return {@link boolean} - if deleted return true else false
     **/
    public boolean deleteTraineeById(int id);

    /**
     * <p>
     * Update Trainee details by id of the trainee in the database.
     * </p>
     * 
     * @param {@link Trainee} newTrainee - trainee Object that contails all the details.
     *
     * @return {@link void} return nothing
     * @throws EmployeeNotFound 
     **/
    public void updateTraineeDetails(Trainee trainee) throws EmployeeNotFound;

    /**
     * <p>
     * Check id with the DataBase if matched return Trainee Object.
     * </p>
     * 
     * @param {@link int} id - trainee Object that contails all the details.
     *
     * @return {@link Trainee} if id exist return new trainer object else null.
     **/
    public Trainee retrieveTraineeById(int id);

}