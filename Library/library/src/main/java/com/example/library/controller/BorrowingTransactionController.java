package com.example.library.controller;

import com.example.library.dto.BorrowingTransactionDto;
import com.example.library.service.BorrowingTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class BorrowingTransactionController {

    @Autowired
    private BorrowingTransactionService transactionService;

    @PostMapping("/borrow")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<BorrowingTransactionDto> borrowBook(
            @RequestParam Long memberId,
            @RequestParam Long bookCopyId,
            @RequestParam(defaultValue = "14") int days) {
        BorrowingTransactionDto dto =
                transactionService.borrowBook(memberId, bookCopyId, days);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/return")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<BorrowingTransactionDto> returnBook(@RequestParam Long bookCopyId) {
        return ResponseEntity.ok(transactionService.returnBook(bookCopyId));
    }

    @GetMapping("/getAllTransactions")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<BorrowingTransactionDto>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}
