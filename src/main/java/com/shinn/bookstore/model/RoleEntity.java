package com.shinn.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity extends BaseEntity {
    @Column
    private String code;

    @Column
    @Enumerated(EnumType.STRING)
    private ERole name;

    @ManyToMany(mappedBy = "roleEntities")
    private List<UserEntity> userEntities = new ArrayList<>();
}
