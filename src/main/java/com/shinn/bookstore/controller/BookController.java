package com.shinn.bookstore.controller;


import com.shinn.bookstore.dto.BookDTO;
import com.shinn.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
//@CrossOrigin
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookDTO>> getList(@RequestParam(name = "page", required = false) Integer page,
                                                 @RequestParam(name = "limit", required = false) Integer limit,
                                                 @RequestParam(name = "sortBy", required = false) String sortBy) {
        Sort sort = sortBy == null? null: Sort.by(sortBy);
        Pageable pageable = (page != null && limit != null) ?
                (sort != null ? PageRequest.of(page - 1, limit, sort) : PageRequest.of(page - 1, limit)) :
                (sort != null ? PageRequest.of(0, Integer.MAX_VALUE, sort) : PageRequest.of(0, Integer.MAX_VALUE));
        return ResponseEntity.ok(bookService.findAll(pageable));
    }

    @PostMapping
    public BookDTO saveOne(@RequestBody BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }
}
