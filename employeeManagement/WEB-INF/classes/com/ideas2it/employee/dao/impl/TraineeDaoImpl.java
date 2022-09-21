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
    public void insertTrainee(Trainee trainee) throws EmployeeNotFound {

        Qualification existingQualification = null;
        Role existingRole = null;
        List<Trainer> trainers = new ArrayList<>();
        List<Integer> validTrainersId = new ArrayList<>();
        List<Integer> invalidTrainerId = new ArrayList<>();
        Set<Trainer> trainersOfTheTrainee = new HashSet<>();
        Session session = sessionFactory.openSession();
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
        trainers = session.createCriteria(Trainer.class).list();
        boolean isValidTrainerId;
        for (Integer trainerId : trainee.getTrainersId()) {
            isValidTrainerId = false;
            for (Trainer trainer : trainers) {
                if (trainer.getEmployee().getId() == trainerId) {
                    trainersOfTheTrainee.add(trainer);
                    validTrainersId.add(trainerId);
                    isValidTrainerId = true;
                    break;
                }
            }
            if (!isValidTrainerId) {
                invalidTrainerId.add(trainerId);
            }
        }
        trainee.setTrainersId(validTrainersId);
        trainee.setTrainers(trainersOfTheTrainee);
        session.save(trainee);
        transaction.commit();
        session.close();
        if (!invalidTrainerId.isEmpty()) {
            throw new EmployeeNotFound("\nInvalid Trainer Id.\n"
                    + invalidTrainerId.toString().replaceAll("[\\[\\]]",""));
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
        List<Trainee> traineeDetails = new ArrayList<>();
        Session session = sessionFactory.openSession();
        String query = "From Trainee";
        traineeDetails = session.createQuery(query).list();
        session.close();
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trainee = (Trainee)session.createCriteria(Trainee.class)
                  .add(Restrictions.eq("employee.id", id)).uniqueResult();
        if (null != trainee) {
            session.remove(trainee);
            transaction.commit();
        }
        session.close();
        return (trainee != null);
    }

    /**
     * <p>
     * Update Trainee details by id of the trainee in the database.
     * </p>
     *
     * @param {@link Trainee} newTrainee - trainee Object that contains all the details.
     *
     * @return {@link void} return nothing
     * @throws EmployeeNotFound
     **/
    public void updateTraineeDetails(Trainee trainee) throws EmployeeNotFound {
        Qualification existingQualification = null;
        List<Trainer> trainers = new ArrayList<>();
        List<Integer> validTrainersId = new ArrayList<>();
        List<Integer> invalidTrainerId = new ArrayList<>();
        Set<Trainer> trainersOfTheTrainee = new HashSet<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String qualification = trainee.getEmployee().getQualification().getCourse();
        existingQualification = (Qualification)session.createCriteria(Qualification.class)
                                .add(Restrictions.eq("course",qualification)).uniqueResult();
        if (null != existingQualification) {
            trainee.getEmployee().setQualification(existingQualification);
        }
        trainers = session.createCriteria(Trainer.class).list();
        boolean isValidTrainerId;
        for (Integer trainerId : trainee.getTrainersId()) {
            isValidTrainerId = false;
            for (Trainer trainer : trainers) {
                if (trainer.getEmployee().getId() == trainerId) {
                    trainersOfTheTrainee.add(trainer);
                    validTrainersId.add(trainerId);
                    isValidTrainerId = true;
                    break;
                }
            }
            if (!isValidTrainerId) {
                invalidTrainerId.add(trainerId);
            }
        }
        trainee.setTrainersId(validTrainersId);
        trainee.setTrainers(trainersOfTheTrainee);
        session.merge(trainee);
        transaction.commit();
        session.close();
        if (!invalidTrainerId.isEmpty()) {
            throw new EmployeeNotFound("Invalid Trainer Id.\n"
                + invalidTrainerId.toString().replaceAll("[\\[\\]]",""));
        }
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
        Session session = sessionFactory.openSession();
        trainee = (Trainee)session.createCriteria(Trainee.class)
                  .add(Restrictions.eq("employee.id", id)).uniqueResult();
        session.close();
        return trainee;
    }
}