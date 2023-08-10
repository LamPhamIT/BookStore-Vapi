package com.shinn.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractDTO {
    private Long id;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
}
