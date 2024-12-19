package com.transction.TransactionService.Service.Interface;

import com.transction.TransactionService.Dto.TransferRequestDto;
import com.transction.TransactionService.Dto.TransferResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface FundTransferService {
        //public  String ACCOUNT_SERVICE_URL = "http://localhost:8020/accounts";
        TransferResponseDto initiateTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount);

       // TransferResponseDto initiateTransfer(Long fromAccountId, Long toAccountId, Double amount);
        List<com.transction.TransactionService.Model.FundTransfer> getTransactionsByCustomer(Long userId, Map<String, String> queryParams);
        com.transction.TransactionService.Model.FundTransfer getTransactionById(Long transactionId);
        boolean validateTransactionLimit(Long accountId, BigDecimal amount);
        void setupScheduledTransfer(TransferRequestDto requestDto, LocalDateTime scheduleDate);
        void setupRecurringTransfer(TransferRequestDto requestDto, String frequency);
        void cancelScheduledTransfer(Long paymentId);
        void cancelRecurringTransfer(Long paymentId);


}
