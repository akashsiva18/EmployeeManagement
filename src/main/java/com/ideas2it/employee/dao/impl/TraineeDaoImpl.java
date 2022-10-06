package com.ideas2it.employee.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.dao.intf.TraineeDaoIntf;
import com.ideas2it.employee.exception.EmployeeNotFound;
import org.springframework.stereotype.Repository;


/**
 * <h2>TraineeDaoImpl</h2>
 * <p>
 * It done insert, retrieve, delete, update data in the SQL Database.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
@Repository
public class TraineeDaoImpl implements TraineeDaoIntf {

    private static SessionFactory sessionFactory = new Configuration()
                                                   .configure("hibernate/properties/hibernate.cfg.xml").buildSessionFactory();

    /**
     * <p>
     * Insert trainee in the Database
     * </p>
     *
     * @param {@link Trainee} trainee - trainee Object that contains all the details.
     *
     * @throws EmployeeNotFound
     **/
    public void insertOrUpdateTrainee(Trainee trainee) {
        Session session = null;
        try {
            Qualification existingQualification = null;
            Role existingRole = null;
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            existingQualification = (Qualification)session.createQuery("From Qualification where course = :user_course")
                                    .setParameter("user_course", trainee.getEmployee().getQualification().getCourse())
                                    .uniqueResult();
            if (null != existingQualification) {
                trainee.getEmployee().setQualification(existingQualification);
            }
            existingRole = (Role)session.createQuery("From Role where description = :description")
                           .setParameter("description", trainee.getEmployee().getRole().getDescription())
                           .uniqueResult();
            if (null != existingRole) {
                trainee.getEmployee().setRole(existingRole);
            }
            session.saveOrUpdate(trainee);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     * Get data from Database and return as List of Trainees.
     * </p>
     *
     * @return {@link List<Trainee>} return List of Trainees.
     **/
    public List<Trainee> retrieveTrainees() {
        Session session = null;
        List<Trainee> traineeDetails = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            String query = "From Trainee";
            traineeDetails = session.createQuery(query).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return traineeDetails;
    }

    /**
     * <p>
     * Delete Trainee by using Id of the trainee in the Database. 
     * </p>
     *
     * @param {@link int} id - used to check the trainee id is available.
     *
     * @return {@link boolean} - if deleted return true else false
     **/
    public boolean deleteTraineeById(int id) {
        Trainee trainee = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            trainee = (Trainee)session.createQuery("From Trainee where employee.id = :id")
                      .setParameter("id", id).uniqueResult();
            if (null != trainee) {
                session.remove(trainee);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (trainee != null);
    }

    /**
     * <p>
     * Check id with the DataBase if matched return Trainee Object.
     * </p>
     *
     * @param {@link int} id - trainee Object that contains all the details.
     *
     * @return {@link Trainee} if id exist return new trainer object else null.
     **/
    public Trainee retrieveTraineeById(int id) {
        Trainee trainee = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            trainee = (Trainee)session.createQuery("From Trainee where employee.id = :id")
                      .setParameter("id", id).uniqueResult();
            trainee.getEmployee().setName("AKASH");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return trainee;
    }
}