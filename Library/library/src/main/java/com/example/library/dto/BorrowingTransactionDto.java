package com.example.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BorrowingTransactionDto {
    private Long id;
    private Long memberId;
    private Long bookCopyId;
    private Long userId;
    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
}
