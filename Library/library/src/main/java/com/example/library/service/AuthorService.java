package com.example.library.service;

import com.example.library.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
     AuthorDto addAuthor(AuthorDto dto);
     void deleteById(Long id);
    AuthorDto updateAuthor(Long id, AuthorDto dto);
     List<AuthorDto> getAll();
     List<AuthorDto> getByFirstName(String name);
}
