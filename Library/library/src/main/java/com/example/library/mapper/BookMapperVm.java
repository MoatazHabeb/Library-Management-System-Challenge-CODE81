package com.example.library.mapper;

import com.example.library.dto.BookDto;
import com.example.library.vm.BookVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapperVm {
    BookMapperVm BOOK_MAPPER_VM = Mappers.getMapper(BookMapperVm.class);

    BookVm toVm(BookDto dto);
    List<BookVm> toVmList(List<BookDto> dtos);
}
