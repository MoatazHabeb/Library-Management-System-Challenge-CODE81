package com.example.library.mapper;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookDto;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.vm.AuthorVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {
    AuthorMapper AUTHOR_MAPPER = Mappers.getMapper(AuthorMapper.class);

    Author toEntity(AuthorDto authorDto);
    List<Author> toEntityList(List<AuthorDto> authorDtos);


    AuthorDto toDto(Author author);
    List<AuthorDto> toDtoList( List<Author> authors);

    AuthorVm toVm(AuthorDto dto);
    List<AuthorVm> toVmList(List<AuthorDto> dtos);
}
