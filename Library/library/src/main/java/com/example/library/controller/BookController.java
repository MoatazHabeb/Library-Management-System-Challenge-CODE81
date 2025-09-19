package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.mapper.BookMapperVm;
import com.example.library.vm.BookVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.library.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto dto) {
        return ResponseEntity.ok(bookService.addBook(dto));
    }

    @DeleteMapping("/deleteBookById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateBookById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BookVm> updateBook(@PathVariable Long id, @RequestBody BookDto dto) {
        BookDto bookDto = bookService.updateBook(id, dto);
        return ResponseEntity.ok(BookMapperVm.BOOK_MAPPER_VM.toVm(bookDto));
    }

    @GetMapping("/getAllBooks")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<List<BookVm>> getAllBooks() {
        return ResponseEntity.ok(
                BookMapperVm.BOOK_MAPPER_VM.toVmList(bookService.getAll())
        );
    }

    @GetMapping("/getBookByName/search")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<List<BookVm>> getBookByName(@RequestParam String name) {
        return ResponseEntity.ok(
                BookMapperVm.BOOK_MAPPER_VM.toVmList(bookService.getByName(name))
        );
    }
}
