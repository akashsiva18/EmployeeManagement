package com.ideas2it.employee.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.dao.intf.TraineeDaoIntf;
import com.ideas2it.employee.exception.EmployeeNotFound;

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
public class TraineeDaoImpl implements TraineeDaoIntf {

    private static SessionFactory sessionFactory = new Configuration()
                                                   .configure().buildSessionFactory();

    /**
     * <p>
     * Insert trainee in the Database
     * </p>
     *
     * @param {@link Trainee} trainee - trainee Object that contains all the details.
     *
     * @return {@link void} return nothing 
     * @throws EmployeeNotFound
     **/
    public void insertTrainee(Trainee trainee) {
        Session session = null;
        try {
            Qualification existingQualification = null;
            Role existingRole = null;
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String qualification = trainee.getEmployee().getQualification().getCourse();
            existingQualification = (Qualification)session.createCriteria(Qualification.class)
                    .add(Restrictions.eq("course",qualification)).uniqueResult();
            if (null != existingQualification) {
                trainee.getEmployee().setQualification(existingQualification);
            }
            String role = trainee.getEmployee().getRole().getDescription();
            existingRole = (Role)session.createCriteria(Role.class)
                    .add(Restrictions.eq("description",role)).uniqueResult();
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
            trainee = (Trainee)session.createCriteria(Trainee.class)
                    .add(Restrictions.eq("employee.id", id)).uniqueResult();
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
            trainee = (Trainee)session.createCriteria(Trainee.class)
                      .add(Restrictions.eq("employee.id", id)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return trainee;
    }
}