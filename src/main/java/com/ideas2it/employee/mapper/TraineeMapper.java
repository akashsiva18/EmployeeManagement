package com.ideas2it.employee.mapper;

import com.ideas2it.employee.DTO.TraineeDTO;
import com.ideas2it.employee.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
public class TraineeMapper {

    public static Trainee traineeDtoToTrainee(TraineeDTO traineeDTO) {
        Trainee trainee = new Trainee();
        trainee.setId(traineeDTO.getId());
        trainee.setName(traineeDTO.getName());
        trainee.setDateOfBirth(traineeDTO.getDateOfBirth());
        trainee.setGender(traineeDTO.getGender());
        trainee.setQualification(QualificationMapper.qualificationDtoToQualification(traineeDTO.getQualificationDTO()));
        trainee.setAddress(traineeDTO.getAddress());
        trainee.setMobileNumber(traineeDTO.getMobileNumber());
        trainee.setEmailId(traineeDTO.getEmailId());
        trainee.setDateOfJoining(traineeDTO.getDateOfJoining());
        trainee.setRole(RoleMapper.roleDtoToRole(traineeDTO.getRoleDTO()));
        trainee.setTrainingPeriod(traineeDTO.getTrainingPeriod());
        trainee.setTrainersId(traineeDTO.getTrainerIds());
        return trainee;
    }

    public static TraineeDTO traineeToTraineeDTO(Trainee trainee) {
        TraineeDTO traineeDTO = new TraineeDTO();
        traineeDTO.setId(trainee.getId());
        traineeDTO.setName(trainee.getName());
        traineeDTO.setDateOfBirth(trainee.getDateOfBirth());
        traineeDTO.setGender(trainee.getGender());
        traineeDTO.setQualificationDTO(QualificationMapper.qualificationToQualificationDto(trainee.getQualification()));
        traineeDTO.setAddress(trainee.getAddress());
        traineeDTO.setMobileNumber(trainee.getMobileNumber());
        traineeDTO.setEmailId(trainee.getEmailId());
        traineeDTO.setDateOfJoining(trainee.getDateOfJoining());
        traineeDTO.setRoleDTO(RoleMapper.roleToRoleDto(trainee.getRole()));
        traineeDTO.setTrainingPeriod(trainee.getTrainingPeriod());
        traineeDTO.setTrainerIds(trainee.getTrainersId());
        List<String> trainerNames = new ArrayList<>();
        trainee.getTrainers().forEach(trainer -> trainerNames.add(trainer.getName()));
        traineeDTO.setTrainersName(trainerNames.toString()
                .replace("[","").replace("]",""));
        return traineeDTO;
    }

}

