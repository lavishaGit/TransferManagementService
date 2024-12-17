package com.transction.TransactionService.Service.Interface;


import com.transction.TransactionService.Dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
    TransactionDto getTransactionById(Long transactionId);
    List<TransactionDto> getTransactionsByAccountId(Long accountId);
    List<TransactionDto> getAllTransactions();
}
