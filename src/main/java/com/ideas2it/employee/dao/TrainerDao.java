package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h2>TrainerDao</h2>
 * <p>
 * It done insert, retrive, delete, update data in the Database.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
@Repository
public interface TrainerDao extends JpaRepository<Trainer, Integer> {
}
