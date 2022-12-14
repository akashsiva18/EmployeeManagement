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
 * It done insert, retrive, delete, update data in the list.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
public class TrainerDaoImpl implements TrainerDaoIntf {
    private static SessionFactory sessionFactory = new Configuration()
                                                       .configure().buildSessionFactory();

    /**
     * <p>
     * Insert trainer in the database.
     * </p>
     * 
     * @param {@link Trainer} trainer - trainer Object that contails all the details.
     *
     * @return {@link void} return nothing 
     **/
    public void insertTrainer(Trainer trainer) {
        Qualification existingQualification = null;
        Role existingRole = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String qualification = trainer.getEmployee().getQualification().getCourse();
        existingQualification = (Qualification)session.createCriteria(Qualification.class)
                                    .add(Restrictions.eq("course",qualification)).uniqueResult();
        if (null != existingQualification) {
            trainer.getEmployee().setQualification(existingQualification);
        }  
        String role = trainer.getEmployee().getRole().getDescription();
        existingRole = (Role)session.createCriteria(Role.class)
                           .add(Restrictions.eq("description",role)).uniqueResult();
        if (null != existingRole) {
            trainer.getEmployee().setRole(existingRole);
        }
        session.save(trainer);
        transaction.commit();
        session.close();
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
        Session session = sessionFactory.openSession();
        String query = "From Trainer";
        trainerDetails = session.createQuery(query).list();
        session.close();
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trainer = (Trainer)session.createCriteria(Trainer.class)
                      .add(Restrictions.eq("employee.id", id)).uniqueResult();
        if (null != trainer) {
            session.remove(trainer);
            transaction.commit();
        }
        session.close();
        return (null != trainer);
    }

    /**
     * <p>
     * Update Trainer details by replace the object in the list by comparing the objects.
     * </p>
     * 
     * @param {@link Trainer} trainer - trainer Object that contails all the details.
     *
     * @return {@link void} return nothing 
     **/
    public void updateTrainerDetails(Trainer trainer) {
        Qualification existingQualification = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String qualification = trainer.getEmployee().getQualification().getCourse();
        existingQualification = (Qualification)session.createCriteria(Qualification.class)
                                    .add(Restrictions.eq("course",qualification)).uniqueResult();
        if (null != existingQualification) {
            trainer.getEmployee().setQualification(existingQualification);
        }
        session.merge(trainer);
        transaction.commit();
        session.close();
    }

    /**
     * <p>
     * return new trainer object that conations the old information in the given id of the trainer.
     * </p>
     * 
     * @param {@link String} id - used to check the trainer is available.
     *
     * @return {@link Trainer} if id exist return new trainer object else null.
     **/
    public Trainer retriveTrainerById(int id) {
        Trainer trainer = null;
        Session session = sessionFactory.openSession();
        trainer = (Trainer)session.createCriteria(Trainer.class)
                      .add(Restrictions.eq("employee.id", id)).uniqueResult();
        session.close();
        return trainer;
    }
}