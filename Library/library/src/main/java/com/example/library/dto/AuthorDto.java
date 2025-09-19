package com.example.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String biography;
    private Long bookId;
}
