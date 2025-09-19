package com.example.library.mapper;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.CategoryDto;
import com.example.library.model.Author;
import com.example.library.model.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "parent.id", target = "parentId")
    CategoryDto toDto(Categories categories);

    @Mapping(source = "parentId", target = "parent.id")
    Categories toEntity(CategoryDto dto);

    List<Categories> toEntityList(List<CategoryDto> categoryDtos);

    List<CategoryDto> toDtoList( List<Categories> category);
}
