package com.ideas2it.employee.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.dao.intf.TrainerDaoIntf;
import com.ideas2it.employee.common.Constant;

/**
 * <h2>TrainerDaoImpl</h2>
 * <p>
 * It done insert, retrieve, delete, update data in the list.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public class TrainerDaoImpl implements TrainerDaoIntf {
    private static SessionFactory sessionFactory = new Configuration()
                                                  .configure("com/ideas2it/employee/config/hibernate.cfg.xml").buildSessionFactory();

    /**
     * <p>
     * Insert trainer in the database.
     * </p>
     * 
     * @param {@link Trainer} trainer - trainer Object that contains all the details.
     *
     * @return {@link void} return nothing 
     **/
    public void insertOrUpdateTrainer(Trainer trainer) {
        Qualification existingQualification = null;
        Role existingRole = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            existingQualification = (Qualification)session.createQuery("From Qualification where course = :user_course")
                                    .setParameter("user_course", trainer.getEmployee().getQualification().getCourse())
                                    .uniqueResult();
            if (null != existingQualification) {
                trainer.getEmployee().setQualification(existingQualification);
            }
            existingRole = (Role)session.createQuery("From Role where description = :description")
                           .setParameter("description", trainer.getEmployee().getRole().getDescription())
                           .uniqueResult();
            if (null != existingRole) {
                trainer.getEmployee().setRole(existingRole);
            }
            session.saveOrUpdate(trainer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     * Return Trainer List.
     * </p>
     * 
     * @return {@link List<Trainer>} return List of Trainers.
     **/
    public List<Trainer> retrieveTrainers() {
        List<Trainer> trainerDetails = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String query = "From Trainer";
            trainerDetails = session.createQuery(query).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return trainerDetails;
    }

    /**
     * <p>
     * Delete Trainer by using Id of the trainer.
     * </p>
     * 
     * @param {@link int} id - used to check the trainer id is available.
     *
     * @return {@link boolean} - if deleted return true else false.
     **/
    public boolean deleteTrainerById(int id) {
        Trainer trainer = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            trainer = (Trainer)session.createQuery("From Trainer where employee.id = :id")
                      .setParameter("id", id).uniqueResult();
            if (null != trainer) {
                session.remove(trainer);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (null != trainer);
    }

    /**
     * <p>
     * return new trainer object that contains the old information in the given id of the trainer.
     * </p>
     * 
     * @param {@link String} id - used to check the trainer is available.
     *
     * @return {@link Trainer} if id exist return new trainer object else null.
     **/
    public Trainer retrieveTrainerById(int id) {
        Trainer trainer = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            trainer = (Trainer)session.createQuery("From Trainer where employee.id = :id")
                      .setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return trainer;
    }
}