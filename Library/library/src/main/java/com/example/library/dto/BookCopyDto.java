package com.example.library.dto;


import com.example.library.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCopyDto {
    private Long id;
    private Long bookId;
    private String barcode;
    private Status status;
}
