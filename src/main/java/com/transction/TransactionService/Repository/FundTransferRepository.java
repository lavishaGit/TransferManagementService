package com.transction.TransactionService.Repository;


import com.transction.TransactionService.Model.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
    public interface FundTransferRepository extends JpaRepository<FundTransfer, Long> {
//        List<FundTransfer> findBySenderAccountIdOrReceiverAccountId(Long senderAccountId, Long receiverAccountId);
//
//        List<FundTransfer> findByReceiverAccountId(Long accountId);
//
//        @Query("SELECT f FROM FundTransfer f WHERE f.initiatedAt >= :startDate AND f.initiatedAt <= :endDate")
//        List<FundTransfer> findByDateRange(LocalDateTime startDate,LocalDateTime endDate);
//
//        List<FundTransfer> findByStatus(String status);


}
