package com.ideas2it.employee.mapper;

import com.ideas2it.employee.DTO.RoleDTO;
import com.ideas2it.employee.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public static RoleDTO roleToRoleDto(Role role) {
        return new RoleDTO(role.getDescription());
    }

    public static Role roleDtoToRole(RoleDTO roleDTO) {
        return new Role(roleDTO.getDescription());
    }

}
