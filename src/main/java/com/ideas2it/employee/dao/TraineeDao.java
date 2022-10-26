package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h2>TraineeDao</h2>
 * <p>
 * It done insert, retrieve, delete, update data in the Database.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
@Repository
public interface TraineeDao extends JpaRepository<Trainee, Integer> {

    @Query("Select te from Trainee te join te.trainers tr where tr.id = ?1")
    public List<Trainee> retrieveTraineesByTrainerId (int id);
}