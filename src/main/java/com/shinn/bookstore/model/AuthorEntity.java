package com.shinn.bookstore.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Author")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity extends BaseEntity{
    @Column
    private String name;

    @OneToMany(mappedBy = "authorEntity")
    private List<BookEntity> bookEntities;

}
