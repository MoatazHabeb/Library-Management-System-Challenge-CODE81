package com.example.library.service.impl;

import com.example.library.dto.AuthorDto;
import com.example.library.mapper.AuthorMapper;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repo.AuthorRepository;
import com.example.library.repo.BookRepository;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public AuthorDto addAuthor(AuthorDto dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + dto.getBookId()));

        Author author = new Author();
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBiography(dto.getBiography());
        author.setBookSet(new HashSet<>());


        Author savedAuthor = authorRepository.save(author);

        savedAuthor.getBookSet().add(book);
        book.getAuthors().add(savedAuthor);

        bookRepository.save(book);

        AuthorDto response = new AuthorDto();
        response.setId(savedAuthor.getId());
        response.setFirstName(savedAuthor.getFirstName());
        response.setLastName(savedAuthor.getLastName());
        response.setBiography(savedAuthor.getBiography());
        response.setBookId(book.getId());

        return response;
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        for (Book book : author.getBookSet()) {
            book.getAuthors().remove(author);
        }

        authorRepository.delete(author);
    }

    @Override
    public AuthorDto updateAuthor(Long id, AuthorDto dto) {
        Author author = authorRepository.findById(id).orElseThrow();
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBiography(dto.getBiography());
        return AuthorMapper.AUTHOR_MAPPER.toDto(authorRepository.save(author));
    }

    @Override
    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream().map(AuthorMapper.AUTHOR_MAPPER::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AuthorDto> getByFirstName(String name) {
        return authorRepository.findByFirstNameContainingIgnoreCase(name)
                .stream().map(AuthorMapper.AUTHOR_MAPPER::toDto).collect(Collectors.toList());
    }
}
