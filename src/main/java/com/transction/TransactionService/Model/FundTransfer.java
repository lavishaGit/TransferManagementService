package com.transction.TransactionService.Model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "fund_transfers")
public class FundTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transfer_Id;

    private Long sender_account_id;  //fromAccountId; // Foreign Key to Account table

    private Long receiver_account_id;  //toAccountId;   // Foreign Key to Account table


    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    private String status = "pending";

    @Column(nullable = false)
    private LocalDateTime initiated_At = LocalDateTime.now();

    private LocalDateTime completed_At;

    // Getters and Setters
}
