package com.example.library.service.impl;

import com.example.library.dto.BookDto;
import com.example.library.mapper.BookMapper;
import com.example.library.model.Book;
import com.example.library.repo.BookRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDto addBook(BookDto dto) {
        Book book = BookMapper.BOOK_MAPPER.toEntity(dto);
        return BookMapper.BOOK_MAPPER.toDto(bookRepository.save(book));
    }
    @Override
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("error.idNotFound");
        }
        bookRepository.deleteById(id);
    }
    @Override
    public BookDto updateBook(Long id, BookDto dto) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setName(dto.getName());
        book.setIsbn(dto.getIsbn());
        book.setEdition(dto.getEdition());
        book.setPublicationYear(dto.getPublicationYear());
        book.setLanguage(dto.getLanguage());
        book.setSummary(dto.getSummary());
        book.setCoverUrl(dto.getCoverUrl());
        return BookMapper.BOOK_MAPPER.toDto(bookRepository.save(book));
    }
    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(BookMapper.BOOK_MAPPER::toDto).collect(Collectors.toList());
    }
    @Override
    public List<BookDto> getByName(String name) {
        return bookRepository.findByNameContainingIgnoreCase(name)
                .stream().map(BookMapper.BOOK_MAPPER::toDto).collect(Collectors.toList());
    }
}

