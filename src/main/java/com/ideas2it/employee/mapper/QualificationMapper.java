package com.ideas2it.employee.mapper;

import com.ideas2it.employee.DTO.QualificationDTO;
import com.ideas2it.employee.model.Qualification;
import org.springframework.stereotype.Component;

@Component
public class QualificationMapper {
    public Qualification qualificationDtoToQualification(QualificationDTO qualificationDTO) {
        return new Qualification(qualificationDTO.getCourse());
    }

    public QualificationDTO qualificationToQualificationDto(Qualification qualification) {
        return new QualificationDTO(qualification.getCourse());
    }
}
