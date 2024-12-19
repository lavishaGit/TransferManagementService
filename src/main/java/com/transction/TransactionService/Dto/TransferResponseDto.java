package com.transction.TransactionService.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferResponseDto {
    private Long transferId;
    private String status;
    private LocalDateTime initiatedAt;
    private LocalDateTime completedAt;
}
