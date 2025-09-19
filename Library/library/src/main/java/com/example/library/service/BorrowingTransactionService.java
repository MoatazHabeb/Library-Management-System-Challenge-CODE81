package com.example.library.service;

import com.example.library.dto.BorrowingTransactionDto;

import java.util.List;

public interface BorrowingTransactionService {
    BorrowingTransactionDto borrowBook(Long memberId, Long bookCopyId, int days);
    BorrowingTransactionDto returnBook(Long bookCopyId);
    List<BorrowingTransactionDto> getAllTransactions();
}
