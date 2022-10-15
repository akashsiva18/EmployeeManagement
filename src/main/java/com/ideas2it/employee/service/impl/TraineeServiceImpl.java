package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.DTO.TraineeDTO;
import com.ideas2it.employee.dao.QualificationDao;
import com.ideas2it.employee.dao.RoleDao;
import com.ideas2it.employee.mapper.TraineeMapper;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.dao.TraineeDao;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.util.DateUtil;
import com.ideas2it.employee.util.StringUtil;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * <h2>TraineeServiceImpl</h2>
 * <p>
 * It done insert, retrieve, delete, update data in the database.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
@Service
public class TraineeServiceImpl implements TraineeService {

    private TraineeDao traineeDao;
    private QualificationDao qualificationDao;
    private RoleDao roleDao;
    private TrainerService trainerService;
    private TraineeMapper traineeMapper;

    @Autowired
    public TraineeServiceImpl ( TraineeDao traineeDao, QualificationDao qualificationDao, RoleDao roleDao,
                                TrainerService trainerService ,TraineeMapper traineeMapper){
        this.traineeDao = traineeDao;
        this.trainerService = trainerService;
        this.qualificationDao = qualificationDao;
        this.roleDao = roleDao;
        this.traineeMapper = traineeMapper;
    }

    /**
     * <p>
     * It valid the details if no error found create object and sent to Dao for add in DataBase
     * else add errors to list. Return List of errors.
     * </p>
     *
     * @param {@link Trainee} trainee - object of the trainee.
     *
     * @return {@link List<Integer>} return List of errors.
     * @throws BadRequest
     **/
    @Override
    public List<Integer> validateAndAddOrUpdateTraineeDetails(TraineeDTO traineeDTO) throws BadRequest {

        List<Integer> validationErrorList = new ArrayList<>();
        Set<Trainer> trainersOfTrainee;
        Integer slNo = 1;
        StringBuilder errorMessage = new StringBuilder("\nValidation Errors\n");
        if (!StringUtil.isValidName(traineeDTO.getName())) {
            errorMessage.append(slNo++).append(". Invalid Name. It must contain Alphabet.\n");
            validationErrorList.add(1);
        }
        if (!DateUtil.isAgeEligible(traineeDTO.getDateOfBirth(),18)) {
            errorMessage.append(slNo++).append(". Invalid Date Of Birth.\n\t")
                    .append("\n\tb) Age must above 18.\n");
            validationErrorList.add(2);
        }
        String mobileNumber = Long.toString(traineeDTO.getMobileNumber());
        if (!StringUtil.isValidMobileNumber(mobileNumber)) {
            errorMessage.append(slNo++).append(". Invalid Mobile Number. It must contain 10 digits\n");
            validationErrorList.add(3);
        }
        if (!StringUtil.isEmailValid(traineeDTO.getEmailId())) {
            errorMessage.append(slNo++).append(". Invalid Mail Id.\n");
            validationErrorList.add(4);
        }
        if (DateUtil.isFutureDate(traineeDTO.getDateOfJoining())) {
            errorMessage.append(slNo++).append(". Date of joining must not be a future Date.\n");
            validationErrorList.add(5);
        }

        if (validationErrorList.isEmpty()) {
            Trainee trainee = traineeMapper.traineeDtoToTrainee(traineeDTO);
            Optional<Qualification> retrieveQualification = qualificationDao
                    .findByCourse(trainee.getQualification().getCourse());
            retrieveQualification.ifPresent(trainee::setQualification);
            Optional<Role> retrieveRole = roleDao.findByDescription("Trainee");
            if (retrieveRole.isPresent()) {
                trainee.setRole(retrieveRole.get());
            }
            trainersOfTrainee = new HashSet<>(trainerService.getMultipleTrainerByIds(trainee.getTrainersId()));
            trainee.setTrainers(trainersOfTrainee);
            traineeDao.save(trainee);
        } else {
            throw new BadRequest(errorMessage.toString(), validationErrorList);
        }
        return validationErrorList;
    }

    /**
     * <p>
     * Return Trainee List.
     * </p>
     * 
     * @return {@link List<Trainee>} return List of Trainees.
     **/
    @Override
    public List<TraineeDTO> getTrainees() {
        List<TraineeDTO> traineeDtoAsList = new ArrayList<>();
        traineeDao.findAll().forEach(trainee -> traineeDtoAsList.add(traineeMapper.traineeToTraineeDTO(trainee)));
        return traineeDtoAsList;
    }  

    /**
     * <p>
     * Remove Trainee by using Id.
     * </p>
     *
     * @param {@link int} id.
     **/
    @Override
    public void removeTraineeById(int id) {
        traineeDao.deleteById(id);
    }

    /**
     * <p>
     * return trainee by using the id
     * </p>
     * 
     * @param {@link int} id - trainee Object that contains all the details.
     *
     * @return {@link Trainee}.
     **/
    @Override
    public TraineeDTO getTraineeById(int id) {
        Trainee trainee = null;
        Optional<Trainee> getTrainee = traineeDao.findById(id);
        if (getTrainee.isPresent()) {
            trainee = getTrainee.get();
        }
        List<Integer> trainerIds = new ArrayList<>();
        for (Trainer trainer : trainee.getTrainers()) {
            trainerIds.add(trainer.getId());
        }
        trainee.setTrainersId(trainerIds);
        return traineeMapper.traineeToTraineeDTO(trainee);
    }
}