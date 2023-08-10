package com.shinn.bookstore.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonInclude(JsonInclude.Include.NON_NULL)  // Don't write null field into Json string response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO extends AbstractDTO{
    private String name;
}
