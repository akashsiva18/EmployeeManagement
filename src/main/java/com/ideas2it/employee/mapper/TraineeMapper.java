package com.ideas2it.employee.mapper;

import com.ideas2it.employee.DTO.TraineeDTO;
import com.ideas2it.employee.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TraineeMapper {

    QualificationMapper qualificationMapper;

    RoleMapper roleMapper;

    @Autowired
    public TraineeMapper (QualificationMapper qualificationMapper, RoleMapper roleMapper) {
        this.qualificationMapper = qualificationMapper;
        this.roleMapper = roleMapper;
    }

    public Trainee traineeDtoToTrainee(TraineeDTO traineeDTO) {
        Trainee trainee = new Trainee();
        trainee.setId(traineeDTO.getId());
        trainee.setName(traineeDTO.getName());
        trainee.setDateOfBirth(traineeDTO.getDateOfBirth());
        trainee.setGender(traineeDTO.getGender());
        trainee.setQualification(qualificationMapper.qualificationDtoToQualification(traineeDTO.getQualificationDTO()));
        trainee.setAddress(traineeDTO.getAddress());
        trainee.setMobileNumber(traineeDTO.getMobileNumber());
        trainee.setEmailId(traineeDTO.getEmailId());
        trainee.setDateOfJoining(traineeDTO.getDateOfJoining());
        trainee.setRole(roleMapper.roleDtoToRole(traineeDTO.getRoleDTO()));
        trainee.setTrainingPeriod(traineeDTO.getTrainingPeriod());
        trainee.setTrainersId(traineeDTO.getTrainerIds());
        return trainee;
    }

    public TraineeDTO traineeToTraineeDTO(Trainee trainee) {
        TraineeDTO traineeDTO = new TraineeDTO();
        traineeDTO.setId(trainee.getId());
        traineeDTO.setName(trainee.getName());
        traineeDTO.setDateOfBirth(trainee.getDateOfBirth());
        traineeDTO.setGender(trainee.getGender());
        traineeDTO.setQualificationDTO(qualificationMapper.qualificationToQualificationDto(trainee.getQualification()));
        traineeDTO.setAddress(trainee.getAddress());
        traineeDTO.setMobileNumber(trainee.getMobileNumber());
        traineeDTO.setEmailId(trainee.getEmailId());
        traineeDTO.setDateOfJoining(trainee.getDateOfJoining());
        traineeDTO.setRoleDTO(roleMapper.roleToRoleDto(trainee.getRole()));
        traineeDTO.setTrainingPeriod(trainee.getTrainingPeriod());
        traineeDTO.setTrainerIds(trainee.getTrainersId());
        List<String> trainerNames = new ArrayList<>();
        trainee.getTrainers().forEach(trainer -> trainerNames.add(trainer.getName()));
        traineeDTO.setTrainersName(trainerNames.toString()
                .replace("[","").replace("]",""));
        return traineeDTO;
    }

}

