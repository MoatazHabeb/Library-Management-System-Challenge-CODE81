package com.example.library.model;

import com.example.library.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // unique identifier for each copy
    private String barcode;

    @Enumerated(EnumType.STRING)
    private Status status;
}
