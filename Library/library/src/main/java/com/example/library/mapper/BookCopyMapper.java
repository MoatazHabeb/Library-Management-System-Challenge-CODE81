package com.example.library.mapper;

import com.example.library.dto.BookCopyDto;
import com.example.library.dto.jwt.UserDto;
import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import com.example.library.model.usermodel.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookCopyMapper {
    BookCopyMapper BOOK_COPY_MAPPER = Mappers.getMapper(BookCopyMapper.class);

    @Mapping(target = "book", ignore = true)
    BookCopy toEntity(BookCopyDto bookCopyDto);

    List<BookCopy> toEntityList(List<BookCopyDto> bookCopyDtos);

    @Mapping(source = "book.id", target = "bookId")
    BookCopyDto toDto(BookCopy bookCopy);

    List<BookCopyDto> toDtoList(List<BookCopy> bookCopies);


    default BookCopy toEntityWithBook(BookCopyDto dto, Book book) {
        BookCopy copy = toEntity(dto);
        copy.setBook(book);
        return copy;
    }
}
