package com.example.library.repo;

import com.example.library.model.BorrowingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
    Optional<BorrowingTransaction> findByBookCopyIdAndReturnDateIsNull(Long bookCopyId);
}
