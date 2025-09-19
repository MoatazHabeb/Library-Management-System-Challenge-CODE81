package com.example.library.controller;

import com.example.library.dto.BookCopyDto;
import com.example.library.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/copies")
public class BookCopyController {

    @Autowired
    private BookCopyService bookCopyService;

    @PostMapping("/addCopy")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<BookCopyDto> addCopy(@RequestBody BookCopyDto dto) {
        BookCopyDto saved = bookCopyService.addCopy(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @PutMapping("/updateCopyById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BookCopyDto> updateCopyById(@PathVariable Long id, @RequestBody BookCopyDto dto) {
        BookCopyDto updated = bookCopyService.updateCopyById(id, dto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/deleteCopyById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteCopyById(@PathVariable Long id) {
        bookCopyService.deleteCopyById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/getAllCopies")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<List<BookCopyDto>> getAllCopies() {
        return ResponseEntity.ok(bookCopyService.getAllCopies());
    }


    @GetMapping("/getByBarcode/{barcode}")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<BookCopyDto> getByBarcode(@PathVariable String barcode) {
        return ResponseEntity.ok(bookCopyService.getByBarcode(barcode));
    }
}
