package com.example.library.service;

import com.example.library.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto addBook(BookDto dto);
    void deleteById(Long id);
   BookDto updateBook(Long id, BookDto dto);
    List<BookDto> getAll();
    List<BookDto> getByName(String name);
}
