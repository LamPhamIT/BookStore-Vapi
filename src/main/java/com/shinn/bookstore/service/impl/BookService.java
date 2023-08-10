package com.shinn.bookstore.service.impl;


import com.shinn.bookstore.converter.BookConverter;
import com.shinn.bookstore.dto.BookDTO;
import com.shinn.bookstore.model.BookEntity;
import com.shinn.bookstore.repository.BookRepository;
import com.shinn.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookConverter bookConverter;

    @Override
    public Page<BookDTO> findAll(Pageable pageable) {
        Page<BookEntity> bookEntities = bookRepository.findAll(pageable);
        return bookEntities.map(bookConverter::toBookDTO);
    }

    @Override
    public List<BookDTO> findAll() {
        return bookConverter.toBookDTO(bookRepository.findAll());
    }

    @Override
    public int totalItem() {
        return (int) bookRepository.count();
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        BookEntity bookEntity = bookConverter.toBookEntity(bookDTO);
        return bookConverter.toBookDTO(bookRepository.save(bookEntity));
    }
}
