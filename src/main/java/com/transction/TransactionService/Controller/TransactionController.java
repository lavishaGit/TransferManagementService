package com.transction.TransactionService.Controller;

import com.transction.TransactionService.Dto.TransactionDto;
import com.transction.TransactionService.Service.Interface.TransactionService;
import com.transction.TransactionService.Service.TransactionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {


    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    // Create a new transaction
    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDto transactionDto) {
   transactionService.createTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("createdTransaction");
    }

    // Get a transaction by ID
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long transactionId) {
        TransactionDto transaction = transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(transaction);
    }

    // Get all transactions for a specific account
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<TransactionDto> transactions = transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }

    // Get all transactions
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}

