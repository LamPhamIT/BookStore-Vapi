package com.shinn.bookstore.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


//@JsonInclude(JsonInclude.Include.NON_NULL)  // Don't write null field into Json string response
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO extends AbstractDTO{
    private String name;
    private Long price;
    private Date releaseDate;
    private AuthorDTO author;
    private CategoryDTO category;
}
