package com.shinn.bookstore.service;

import com.shinn.bookstore.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {
    public Page<BookDTO> findAll(Pageable pageable);
    public List<BookDTO> findAll();
    public int totalItem();

    public BookDTO save(BookDTO bookDTO);
}
