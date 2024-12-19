package com.transction.TransactionService.Controller;

import com.transction.TransactionService.Dto.TransferRequestDto;
import com.transction.TransactionService.Dto.TransferResponseDto;
import com.transction.TransactionService.Model.FundTransfer;
import com.transction.TransactionService.Service.FundTransferServiceImpl;
import com.transction.TransactionService.Service.Interface.FundTransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fund-transfer")
@AllArgsConstructor
public class FundTransferController {


    private FundTransferServiceImpl fundTransferService;

    @PostMapping
    public ResponseEntity<TransferResponseDto> initiateTransfer(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam BigDecimal amount) {

        try {
            TransferResponseDto responseDto = fundTransferService.initiateTransfer(fromAccountId, toAccountId, amount);

            return ResponseEntity.ok(responseDto); // Returning TransferResponseDto
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new TransferResponseDto());
        }
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<FundTransfer> getTransactionById(@PathVariable Long transactionId) {
        FundTransfer transaction = fundTransferService.getTransactionById(transactionId);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<FundTransfer>> getTransactionsByCustomer(
            @RequestParam Long userId,
            @RequestParam Map<String, String> queryParams) {
        List<FundTransfer> transactions = fundTransferService.getTransactionsByCustomer(userId, queryParams);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/limits/{accountId}/validate")
    public ResponseEntity<Boolean> validateTransactionLimit(
            @PathVariable Long accountId, @RequestParam BigDecimal amount) {
        boolean isValid = fundTransferService.validateTransactionLimit(accountId, amount);
        return ResponseEntity.ok(isValid);
    }
}

