package com.ideas2it.employee.mapper;

import com.ideas2it.employee.DTO.TrainerDTO;
import com.ideas2it.employee.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {

    QualificationMapper qualificationMapper;
    RoleMapper roleMapper;

    @Autowired
    public TrainerMapper (QualificationMapper qualificationMapper, RoleMapper roleMapper) {
        this.qualificationMapper = qualificationMapper;
        this.roleMapper = roleMapper;
    }

    public Trainer trainerDtoToTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = new Trainer();
        trainer.setId(trainerDTO.getId());
        trainer.setName(trainerDTO.getName());
        trainer.setDateOfBirth(trainerDTO.getDateOfBirth());
        trainer.setGender(trainerDTO.getGender());
        trainer.setQualification(qualificationMapper.qualificationDtoToQualification(trainerDTO.getQualificationDTO()));
        trainer.setAddress(trainerDTO.getAddress());
        trainer.setMobileNumber(trainerDTO.getMobileNumber());
        trainer.setEmailId(trainerDTO.getEmailId());
        trainer.setDateOfJoining(trainerDTO.getDateOfJoining());
        trainer.setRole(roleMapper.roleDtoToRole(trainerDTO.getRoleDTO()));
        trainer.setExperience(trainerDTO.getExperience());
        return trainer;
    }

    public TrainerDTO trainerToTrainerDTO(Trainer trainer) {
        TrainerDTO trainerDTO = new TrainerDTO();
        trainerDTO.setId(trainer.getId());
        trainerDTO.setName(trainer.getName());
        trainerDTO.setDateOfBirth(trainer.getDateOfBirth());
        trainerDTO.setGender(trainer.getGender());
        trainerDTO.setQualificationDTO(qualificationMapper.qualificationToQualificationDto(trainer.getQualification()));
        trainerDTO.setAddress(trainer.getAddress());
        trainerDTO.setMobileNumber(trainer.getMobileNumber());
        trainerDTO.setEmailId(trainer.getEmailId());
        trainerDTO.setDateOfJoining(trainer.getDateOfJoining());
        trainerDTO.setRoleDTO(roleMapper.roleToRoleDto(trainer.getRole()));
        trainerDTO.setExperience(trainer.getExperience());
        trainerDTO.setNoOfTrainees(trainer.getTrainees().size());
        return trainerDTO;
    }



}