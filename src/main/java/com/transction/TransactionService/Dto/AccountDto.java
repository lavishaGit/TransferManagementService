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
public class AccountDto {
//

    private Long userId;
    private Long accountID;
    private String accountNumber;// VARCHAR(20) UNIQUE, NOT NULL
    private String accountType;
    private String currency; // VARCHAR(10) with default value 'USD'
    private BigDecimal balance; // DECIMAL(15,2) with default value 0.0
    private LocalDateTime createdAt;
//    "userId": 9,
//            "accountID": 27,
//            "accountNumber": "3457",
//            "accountType": "saving",
//            "currency": "USD",
//            "balance": 9000.0,
//            "createdAt": "2024-12-16T17:15:11Z"
}
