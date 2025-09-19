package com.example.library.vm;

import com.example.library.dto.CategoryDto;
import com.example.library.dto.PublisherDto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BookVm {
    private Long id;
    private String name;
    private String isbn;
    private String edition;
    private Integer publicationYear;
    private String language;
    private String summary;
    private String coverUrl;

    private PublisherDto publisher;
    private Set<AuthorVm> authors;
    private Set<CategoryDto> category;
}
