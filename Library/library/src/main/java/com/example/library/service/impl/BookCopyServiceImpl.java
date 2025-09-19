package com.example.library.service.impl;

import com.example.library.dto.BookCopyDto;
import com.example.library.mapper.BookCopyMapper;
import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import com.example.library.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.library.repo.BookCopyRepository;
import com.example.library.repo.BookRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookCopyServiceImpl implements BookCopyService {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;
    @Override
    public BookCopyDto addCopy(BookCopyDto dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        BookCopy copy = BookCopyMapper.BOOK_COPY_MAPPER.toEntityWithBook(dto, book);
        return BookCopyMapper.BOOK_COPY_MAPPER.toDto(bookCopyRepository.save(copy));
    }

    @Override
    public BookCopyDto updateCopyById(Long id, BookCopyDto dto) {
        BookCopy existing = bookCopyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Copy not found"));

        if (dto.getBookId() != null) {
            Book book = bookRepository.findById(dto.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            existing.setBook(book);
        }

        existing.setBarcode(dto.getBarcode());
        existing.setStatus(dto.getStatus());

        return BookCopyMapper.BOOK_COPY_MAPPER.toDto(bookCopyRepository.save(existing));
    }

    @Override
    public void deleteCopyById(Long id) {
        if (!bookCopyRepository.existsById(id)) {
            throw new RuntimeException("Copy not found");
        }
        bookCopyRepository.deleteById(id);
    }

    @Override
    public List<BookCopyDto> getAllCopies() {
        return bookCopyRepository.findAll()
                .stream()
                .map(BookCopyMapper.BOOK_COPY_MAPPER::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookCopyDto getByBarcode(String barcode) {
        BookCopy copy = bookCopyRepository.findByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Copy not found"));
        return BookCopyMapper.BOOK_COPY_MAPPER.toDto(copy);
    }
}
