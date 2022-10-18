package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h2>TraineeDao</h2>
 * <p>
 * It done insert, retrive, delete, update data in the Database.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
@Repository
public interface TraineeDao extends JpaRepository<Trainee, Integer> {
}