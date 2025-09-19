package com.example.library.service;

import com.example.library.dto.BookCopyDto;

import java.util.List;

public interface BookCopyService {
    BookCopyDto addCopy(BookCopyDto dto);
    BookCopyDto updateCopyById(Long id, BookCopyDto dto);
    void deleteCopyById(Long id);
    List<BookCopyDto> getAllCopies();
    BookCopyDto getByBarcode(String barcode);
}
