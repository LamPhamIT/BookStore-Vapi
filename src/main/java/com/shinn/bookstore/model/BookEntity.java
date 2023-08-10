package com.shinn.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="Book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Date releaseDate;

    @ManyToOne //(cascade = CascadeType.PERSIST)  // Save author before save Book if author doesn't exist
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity categoryEntity;
}