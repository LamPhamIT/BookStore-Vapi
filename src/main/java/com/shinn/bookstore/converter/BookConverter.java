package com.shinn.bookstore.converter;

import com.shinn.bookstore.dto.BookDTO;
import com.shinn.bookstore.model.BookEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class BookConverter {
    @Autowired
    private AuthorConverter authorConverter;

    @Autowired
    private CategoryConverter categoryConverter;
    public BookDTO toBookDTO(BookEntity bookEntity) {
        if(bookEntity != null) {
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(bookEntity, bookDTO);
            bookDTO.setAuthor(authorConverter.toAuthorDTO(bookEntity.getAuthorEntity()));
            bookDTO.setCategory(categoryConverter.toCategoryDTO(bookEntity.getCategoryEntity()));
            return bookDTO;
        }
        return null;
    }
    public List<BookDTO> toBookDTO(List<BookEntity> bookEntities) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity bookEntity : bookEntities) {
            results.add(toBookDTO(bookEntity));
        }
        return results;
    }
    public BookEntity toBookEntity(BookDTO bookDTO) {
        if(bookDTO != null) {
            BookEntity bookEntity = new BookEntity();
            BeanUtils.copyProperties(bookDTO, bookEntity);
            bookEntity.setCategoryEntity(categoryConverter.toCategoryEntity(bookDTO.getCategory()));
            bookEntity.setAuthorEntity(authorConverter.toAuthorEntity(bookDTO.getAuthor()));
            return bookEntity;
        }
        return null;
    }
}
