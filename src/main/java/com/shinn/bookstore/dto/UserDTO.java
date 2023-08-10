package com.shinn.bookstore.dto;

import com.shinn.bookstore.model.RoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends AbstractDTO{
    private String email;
    private String username;

    private String password;

    private String fullname;

    private String phone;

    private String address;

    private List<RoleDTO> roles= new ArrayList<>();
}
