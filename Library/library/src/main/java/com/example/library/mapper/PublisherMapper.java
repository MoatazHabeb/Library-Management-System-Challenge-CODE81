package com.example.library.mapper;

import com.example.library.dto.BookDto;
import com.example.library.dto.PublisherDto;
import com.example.library.model.Book;
import com.example.library.model.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PublisherMapper {
    PublisherMapper PUBLISHER_MAPPER = Mappers.getMapper(PublisherMapper.class);
    Publisher toEntity(PublisherDto publisherDto);
    List<Publisher> toEntityList(List<PublisherDto> publisherDtos);


    PublisherDto toDto(Publisher publisher);
    List<PublisherDto> toDtoList( List<Publisher> publishers);
}
