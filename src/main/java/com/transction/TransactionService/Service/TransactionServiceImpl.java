package com.transction.TransactionService.Service;

import com.transction.TransactionService.Dto.TransactionDto;
import com.transction.TransactionService.Model.Transaction;
import com.transction.TransactionService.Repository.TransactionRepository;
import com.transction.TransactionService.Service.Interface.TransactionService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = mapToEntity(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToDto(savedTransaction);
    }


    @Override
    public TransactionDto getTransactionById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + transactionId));
        return mapToDto(transaction);
    }

    @Override
    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        List<TransactionDto> transactionDtos = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionDtos.add(mapToDto(transaction)); // Using mapToDto method
        }
        return transactionDtos;
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDto> transactionDtos = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionDtos.add(mapToDto(transaction)); // Using mapToDto method
        }
        return transactionDtos;
    }

    private TransactionDto mapToDto(Transaction transaction) {
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .accountId(transaction.getAccountId())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .createdAt(transaction.getCreatedAt())
                .build();
    }

    private Transaction mapToEntity(TransactionDto transactionDto) {
        return Transaction.builder()
                .accountId(transactionDto.getAccountId())
                .transactionType(transactionDto.getTransactionType())
                .amount(transactionDto.getAmount())
                .description(transactionDto.getDescription())
                .build();
    }
}
