package com.example.library.vm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorVm {
    private Long id;
    private String firstName;
    private String lastName;
    private String biography;
}
