package com.shinn.bookstore.converter;

import com.shinn.bookstore.dto.AuthorDTO;
import com.shinn.bookstore.model.AuthorEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {
    public AuthorEntity toAuthorEntity(AuthorDTO authorDTO) {
        if(authorDTO != null) {
            AuthorEntity authorEntity = new AuthorEntity();
            BeanUtils.copyProperties(authorDTO, authorEntity);
            return authorEntity;
        }
        return null;
    }
    public AuthorDTO toAuthorDTO(AuthorEntity authorEntity) {
        if(authorEntity != null) {
            AuthorDTO authorDTO = new AuthorDTO();
            BeanUtils.copyProperties(authorEntity, authorDTO);
            return authorDTO;
        }
        return null;
    }
}
