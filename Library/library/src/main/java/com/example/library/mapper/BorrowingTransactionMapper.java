package com.example.library.mapper;

import com.example.library.dto.BorrowingTransactionDto;
import com.example.library.model.BorrowingTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BorrowingTransactionMapper {
    BorrowingTransactionMapper BORROWING_TRANSACTION_MAPPER = Mappers.getMapper(BorrowingTransactionMapper.class);

    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "bookCopy.id", target = "bookCopyId")
    @Mapping(source = "user.id", target = "userId")
    BorrowingTransactionDto toDto(BorrowingTransaction transaction);

    List<BorrowingTransactionDto> toDtoList(List<BorrowingTransaction> transactions);


}
