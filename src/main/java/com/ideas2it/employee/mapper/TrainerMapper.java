package com.ideas2it.employee.mapper;

import com.ideas2it.employee.DTO.TrainerDTO;
import com.ideas2it.employee.model.Trainer;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {

    public static Trainer trainerDtoToTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = new Trainer();
        trainer.setId(trainerDTO.getId());
        trainer.setName(trainerDTO.getName());
        trainer.setDateOfBirth(trainerDTO.getDateOfBirth());
        trainer.setGender(trainerDTO.getGender());
        trainer.setQualification(QualificationMapper.qualificationDtoToQualification(trainerDTO.getQualificationDTO()));
        trainer.setAddress(trainerDTO.getAddress());
        trainer.setMobileNumber(trainerDTO.getMobileNumber());
        trainer.setEmailId(trainerDTO.getEmailId());
        trainer.setDateOfJoining(trainerDTO.getDateOfJoining());
        trainer.setRole(RoleMapper.roleDtoToRole(trainerDTO.getRoleDTO()));
        trainer.setExperience(trainerDTO.getExperience());
        return trainer;
    }

    public static TrainerDTO trainerToTrainerDTO(Trainer trainer) {
        TrainerDTO trainerDTO = new TrainerDTO();
        trainerDTO.setId(trainer.getId());
        trainerDTO.setName(trainer.getName());
        trainerDTO.setDateOfBirth(trainer.getDateOfBirth());
        trainerDTO.setGender(trainer.getGender());
        trainerDTO.setQualificationDTO(QualificationMapper.qualificationToQualificationDto(trainer.getQualification()));
        trainerDTO.setAddress(trainer.getAddress());
        trainerDTO.setMobileNumber(trainer.getMobileNumber());
        trainerDTO.setEmailId(trainer.getEmailId());
        trainerDTO.setDateOfJoining(trainer.getDateOfJoining());
        trainerDTO.setRoleDTO(RoleMapper.roleToRoleDto(trainer.getRole()));
        trainerDTO.setExperience(trainer.getExperience());
        trainerDTO.setNoOfTrainees(trainer.getTrainees().size());
        return trainerDTO;
    }

}