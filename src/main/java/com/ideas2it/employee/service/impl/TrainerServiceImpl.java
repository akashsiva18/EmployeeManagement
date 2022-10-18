package com.ideas2it.employee.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.ideas2it.employee.DTO.TrainerDTO;
import com.ideas2it.employee.dao.QualificationDao;
import com.ideas2it.employee.dao.RoleDao;
import com.ideas2it.employee.dao.TrainerDao;
import com.ideas2it.employee.mapper.TrainerMapper;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.service.TrainerService;
import com.ideas2it.employee.util.DateUtil;
import com.ideas2it.employee.util.StringUtil;
import com.ideas2it.employee.exception.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h2>TrainerServiceImpl</h2>
 * <p>
 * It done insert, retrieve, delete, update data in the DataBase.
 * </p> 
 *
 * @author Akash Siva
 * @version 1.0
 * @since 12-08-2022
 **/
@Service
public class TrainerServiceImpl implements TrainerService {
    private TrainerDao trainerDao;
    private QualificationDao qualificationDao;
    private RoleDao roleDao;

    @Autowired
    public TrainerServiceImpl(TrainerDao trainerDao, QualificationDao qualificationDao,
                              RoleDao roleDao) {
        this.trainerDao = trainerDao;
        this.qualificationDao = qualificationDao;
        this.roleDao = roleDao;
    }

    /**
     * <p>
     * It valid the details if no error found create object and sent to Dao for add in database
     * else add errors to list. Return List of errors.
     * </p>
     *
     * @param {@link Trainer} trainer - Object of the trainer
     *
     * @return {@link List<Integer>} return List of errors.
     * @throws BadRequest
     *
     **/
    @Override
    public List<Integer> validateAndAddOrUpdateTrainerDetails(TrainerDTO trainerDTO) throws BadRequest {
        List<Integer> validationErrorList = new ArrayList<>();
        Integer slNo = 1;
        StringBuilder errorMessage = new StringBuilder("\nValidation Errors\n");
        if (!StringUtil.isValidName(trainerDTO.getName())) {
            errorMessage.append(slNo++).append(". Invalid Name. It must contain Alphabet.\n");
            validationErrorList.add(1);
        }
        if (!DateUtil.isAgeEligible(trainerDTO.getDateOfBirth(),18)) {
            errorMessage.append(slNo++).append(". Invalid Date Of Birth.\n\ta)")
                    .append("\n\tb) Age must above 18.\n");
            validationErrorList.add(2);
        }
        String mobileNumber = Long.toString(trainerDTO.getMobileNumber());
        if (!StringUtil.isValidMobileNumber(mobileNumber)) {
            errorMessage.append(slNo++).append(". Invalid Mobile Number. It must contain 10 digits\n");
            validationErrorList.add(3);
        }
        if (!StringUtil.isEmailValid(trainerDTO.getEmailId())) {
            errorMessage.append(slNo++).append(". Invalid Mail Id.\n");
            validationErrorList.add(4);
        }
        if (DateUtil.isFutureDate(trainerDTO.getDateOfJoining())) {
            errorMessage.append(slNo++).append(". Date of joining must not be a future Date.\n");
            validationErrorList.add(5);
        }
        Trainer trainer = TrainerMapper.trainerDtoToTrainer(trainerDTO);
        if (validationErrorList.isEmpty()) {
            Optional<Qualification> retrieveQualification = qualificationDao
                    .findByCourse(trainer.getQualification().getCourse());
            if (retrieveQualification.isPresent()) {
                trainer.setQualification(retrieveQualification.get());
            }
            Optional<Role> retrieveRole = roleDao.findByDescription("Trainer");
            if (retrieveRole.isPresent()) {
                trainer.setRole(retrieveRole.get());
            }
            trainerDao.save(trainer);
        } else {
            throw new BadRequest(errorMessage.toString(), validationErrorList);
        }
        return validationErrorList;
    }

    /**
     * <p>
     * Return Trainer List.
     * </p>
     *
     * @return {@link List<Trainer>} return List of Trainers.
     **/
    @Override
    public List<TrainerDTO> getTrainers() {
        List<TrainerDTO> trainerDTOList = new ArrayList<>();
        trainerDao.findAll().forEach(trainer -> trainerDTOList.add(TrainerMapper.trainerToTrainerDTO(trainer)));
        return trainerDTOList;
    }

    /**
     * <p>
     * Remove Trainer by using Id of the trainer.
     * </p>
     *
     * @param {@link String} id.
     **/
    @Override
    public void removeTrainerById(int id) {
        trainerDao.deleteById(id);
    }

    /**
     * <p>
     * Return trainer if id is matched else throws Exception.
     * </p>
     *
     * @param {@link int} id - id of the Trainer.
     *
     * @return {@link Trainer}
     *         - that contain a copy of the Trainer matches to the id.
     **/
    @Override
    public TrainerDTO getTrainerById(int id) {
        TrainerDTO trainerDTO = null;
        Optional<Trainer> retrieveTrainer = trainerDao.findById(id);
        if (retrieveTrainer.isPresent()) {
            trainerDTO = TrainerMapper.trainerToTrainerDTO(retrieveTrainer.get());
        }
        return trainerDTO;
    }

    /**
     * It returns the trainers object as list.
     *
     * @param trainerIds - list of Trainers Ids.
     * @return List<Trainers> - it contains the trainers.
     **/
    public List<Trainer> getMultipleTrainerByIds(List<Integer> trainerIds) {
        return trainerDao.findAllById(trainerIds);
    }
}