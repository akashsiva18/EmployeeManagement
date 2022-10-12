package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h2>QualifcationDao</h2>
 * <p>
 * It done insert, retrive, delete, update data in the Database.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-10-2022
 **/
@Repository
public interface QualificationDao extends JpaRepository<Qualification, Integer> {

    Optional<Qualification> findByCourse(String course);
}