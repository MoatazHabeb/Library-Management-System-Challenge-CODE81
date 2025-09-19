package com.example.library.controller;

import com.example.library.dto.AuthorDto;
import com.example.library.mapper.AuthorMapper;
import com.example.library.service.AuthorService;
import com.example.library.vm.AuthorVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto dto) {
        return ResponseEntity.ok(authorService.addAuthor(dto));
    }

    @DeleteMapping("/deleteAuthorById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateAuthorById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<AuthorVm> updateAuthorById(@PathVariable Long id, @RequestBody AuthorDto dto) {
        AuthorDto authorDto = authorService.updateAuthor(id, dto);
        return ResponseEntity.ok(AuthorMapper.AUTHOR_MAPPER.toVm(authorDto));
    }

    @GetMapping("/getAllAuthors")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<List<AuthorVm>> getAllAuthors() {
        List<AuthorDto> authorDtos = authorService.getAll();
        return ResponseEntity.ok(AuthorMapper.AUTHOR_MAPPER.toVmList(authorDtos));
    }

    @GetMapping("/getAuthorsByFirstName/search/firstName")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<List<AuthorVm>> getAuthorsByFirstName(@RequestParam String name) {
        List<AuthorDto> authorDtos = authorService.getByFirstName(name);

        return ResponseEntity.ok( AuthorMapper.AUTHOR_MAPPER.toVmList(authorDtos));
    }

}
