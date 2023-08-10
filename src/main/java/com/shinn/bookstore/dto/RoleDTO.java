package com.shinn.bookstore.dto;

import com.shinn.bookstore.model.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO extends AbstractDTO{
    private String code;
    private ERole name;
}
