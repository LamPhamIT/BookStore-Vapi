package com.shinn.bookstore.converter;

import com.shinn.bookstore.dto.RoleDTO;
import com.shinn.bookstore.dto.UserDTO;
import com.shinn.bookstore.model.RoleEntity;
import com.shinn.bookstore.model.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserConverter {
    @Autowired
    private RoleConverter roleConverter;

    public UserEntity toUserEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO, user);
        if(userDTO.getRoles() != null) {
            List<RoleEntity> roleEntities = new ArrayList<>();

            for(RoleDTO roleDTO : userDTO.getRoles()) {
                RoleEntity roleEntity = roleConverter.toRoleEntity(roleDTO);
                List<UserEntity> userEntities = new ArrayList<>();
                userEntities.add(user);
                roleEntity.setUserEntities(userEntities);
                roleEntities.add(roleEntity);

            }
            user.setRoleEntities(roleEntities);
        }
        return user;
    }
}
