package com.example.library.service.impl;

import com.example.library.dto.BorrowingTransactionDto;
import com.example.library.enums.Status;
import com.example.library.model.BookCopy;
import com.example.library.model.BorrowingTransaction;
import com.example.library.model.Member;
import com.example.library.model.usermodel.Users;
import com.example.library.service.BorrowingTransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.library.repo.*;
import com.example.library.mapper.BorrowingTransactionMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingTransactionServiceImpl implements BorrowingTransactionService {

    @Autowired
    private BorrowingTransactionRepository transactionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Override
    public BorrowingTransactionDto borrowBook(Long memberId, Long bookCopyId, int days) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId)
                .orElseThrow(() -> new RuntimeException("BookCopy not found"));


        transactionRepository.findByBookCopyIdAndReturnDateIsNull(bookCopyId)
                .ifPresent(tx -> { throw new RuntimeException("Book already borrowed"); });


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = (Users) authentication.getPrincipal();


        BorrowingTransaction transaction = new BorrowingTransaction();
        transaction.setMember(member);
        transaction.setBookCopy(bookCopy);
        transaction.setUser(user);
        transaction.setBorrowDate(LocalDateTime.now());
        transaction.setDueDate(LocalDateTime.now().plusDays(days));


        BorrowingTransaction saved = transactionRepository.save(transaction);


        bookCopy.setStatus(Status.BORROWED);
        bookCopyRepository.save(bookCopy);

        bookCopy.setStatus(Status.valueOf("BORROWED"));
        bookCopyRepository.save(bookCopy);

        return BorrowingTransactionMapper.BORROWING_TRANSACTION_MAPPER.toDto(saved);
    }


    @Override
    public BorrowingTransactionDto returnBook(Long bookCopyId) {
        BorrowingTransaction transaction = transactionRepository
                .findByBookCopyIdAndReturnDateIsNull(bookCopyId)
                .orElseThrow(() -> new RuntimeException("Book is not currently borrowed"));

        transaction.setReturnDate(LocalDateTime.now());

        Optional<BookCopy> bookCopy = bookCopyRepository.findById(bookCopyId);
        BookCopy bookCopy1 = bookCopy.get();
        bookCopy1.setStatus(Status.valueOf("AVAILABLE"));
        BorrowingTransaction updated = transactionRepository.save(transaction);
        return BorrowingTransactionMapper.BORROWING_TRANSACTION_MAPPER.toDto(updated);
    }

    @Override
    public List<BorrowingTransactionDto> getAllTransactions() {
        return BorrowingTransactionMapper.BORROWING_TRANSACTION_MAPPER
                .toDtoList(transactionRepository.findAll());
    }
}
