package com.example.library.mapper;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookDto;
import com.example.library.dto.MemberDto;
import com.example.library.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface BookMapper {
    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);


    @Mapping(target = "publisher", source = "publisherId", qualifiedByName = "publisherFromId")
    @Mapping(target = "authors", source = "authorIds", qualifiedByName = "authorsFromIds")
    @Mapping(target = "categories", source = "categoryIds", qualifiedByName = "categoriesFromIds")
    Book toEntity(BookDto dto);

    @Mapping(target = "publisherId", source = "publisher.id")
    @Mapping(target = "authorIds", source = "authors", qualifiedByName = "authorIdsFromAuthors")
    @Mapping(target = "categoryIds", source = "categories", qualifiedByName = "categoryIdsFromCategories")
    BookDto toDto(Book book);

    // Helpers
    @Named("publisherFromId")
    default Publisher publisherFromId(Long id) {
        if (id == null) return null;
        Publisher p = new Publisher();
        p.setId(id);
        return p;
    }

    @Named("authorsFromIds")
    default Set<Author> authorsFromIds(Set<Long> ids) {
        return ids == null ? new HashSet<>() :
                ids.stream().map(id -> {
                    Author a = new Author();
                    a.setId(id);
                    return a;
                }).collect(Collectors.toSet());
    }

    @Named("categoriesFromIds")
    default Set<Categories> categoriesFromIds(Set<Long> ids) {
        return ids == null ? new HashSet<>() :
                ids.stream().map(id -> {
                    Categories c = new Categories();
                    c.setId(id);
                    return c;
                }).collect(Collectors.toSet());
    }

    @Named("authorIdsFromAuthors")
    default Set<Long> authorIdsFromAuthors(Set<Author> authors) {
        return authors == null ? new HashSet<>() :
                authors.stream().map(Author::getId).collect(Collectors.toSet());
    }

    @Named("categoryIdsFromCategories")
    default Set<Long> categoryIdsFromCategories(Set<Categories> categories) {
        return categories == null ? new HashSet<>() :
                categories.stream().map(Categories::getId).collect(Collectors.toSet());
    }

    List<Book> toEntityList(List<BookDto> bookDtos);

    List<BookDto> toDtoList( List<Book> books);

}
