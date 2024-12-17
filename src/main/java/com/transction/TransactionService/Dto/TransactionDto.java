package com.transction.TransactionService.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private Long transactionId;
    private Long accountId;
    private String transactionType;
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
}