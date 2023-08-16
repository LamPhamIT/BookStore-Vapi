package com.shinn.bookstore.converter;

import com.shinn.bookstore.dto.RoleDTO;
import com.shinn.bookstore.model.RoleEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {


    public RoleEntity toRoleEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleDTO, roleEntity);
        return roleEntity;
    }
    public RoleDTO toRoleDTO(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(roleEntity, roleDTO);
        return roleDTO;
    }

}
