package com.example.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String name;
    private String isbn;
    private String edition;
    private Integer publicationYear;
    private String language;
    private String summary;
    private String coverUrl;
    private Long publisherId;
    private Set<Long> authorIds;
    private Set<Long> categoryIds;
}
