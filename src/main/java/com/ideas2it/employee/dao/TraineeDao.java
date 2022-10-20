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

    @Query(
        value = "SELECT * from Trainee te inner join employee_relationship on trainee_id = te.id where trainer_id = ?1",
        nativeQuery = true)
    public List<Trainee> retrieveTraineesByTrainerId (int id);
}