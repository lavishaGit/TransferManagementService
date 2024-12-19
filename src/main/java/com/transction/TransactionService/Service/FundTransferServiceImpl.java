package com.transction.TransactionService.Service;

import com.transction.TransactionService.Dto.AccountDto;
import com.transction.TransactionService.Dto.TransferRequestDto;
import com.transction.TransactionService.Dto.TransferResponseDto;
import com.transction.TransactionService.Model.FundTransfer;
import com.transction.TransactionService.Repository.FundTransferRepository;
import com.transction.TransactionService.Service.Interface.FundTransferService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(force = true)
@Service
@AllArgsConstructor
public class FundTransferServiceImpl implements FundTransferService {
    @Autowired
    private FundTransferRepository fundTransferRepository;
    @Autowired
    private final RestTemplate restTemplate;
    @Value("${account.service.url}")
    public String ACCOUNT_SERVICE_URL;
  //public  String ACCOUNT_SERVICE_URL = "http://localhost:8020/accounts";
  @Override
  public TransferResponseDto initiateTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        // 1. Fetch 'from' account balance
        System.out.println("Fetching account with ID: " + fromAccountId);

      if (restTemplate == null) {
          System.err.println("RestTemplate is not initialized. Please ensure it is properly configured as a Spring Bean.");
          throw new IllegalStateException("RestTemplate is null");
      }

      AccountDto fromAccount = restTemplate.getForObject(
                ACCOUNT_SERVICE_URL + fromAccountId, AccountDto.class);
        System.out.println("Fetched 'from' account: " + fromAccount);

        // 2. Check if sufficient balance exists
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        // 3. Deduct from 'from' account
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        restTemplate.put(ACCOUNT_SERVICE_URL + fromAccountId, fromAccount);
      System.out.println( "successful substracted"+ fromAccount);
        // 4. Fetch and update 'to' account balance
        AccountDto toAccount = restTemplate.getForObject(
                ACCOUNT_SERVICE_URL + toAccountId, AccountDto.class);
      System.out.println( "successful added"+ toAccount);

        toAccount.setBalance(toAccount.getBalance().add(amount));
        restTemplate.put(ACCOUNT_SERVICE_URL + toAccountId, toAccount);

        // 5. Record the transaction
        FundTransfer transaction = new FundTransfer();
        transaction.setSender_account_id(fromAccountId);
        transaction.setReceiver_account_id(toAccountId);
        transaction.setAmount(amount);
        transaction.setInitiated_At(LocalDateTime.now());
        transaction = fundTransferRepository.save(transaction); // Saving transaction in the DB
        System.out.println("Saved transaction: " + transaction);

        // 6. Create TransferResponseDto
        TransferResponseDto responseDto = new TransferResponseDto();
        responseDto.setTransferId(transaction.getTransfer_Id());
        responseDto.setStatus("Success");
        responseDto.setInitiatedAt(transaction.getInitiated_At());
        responseDto.setCompletedAt(LocalDateTime.now());

        return responseDto; // Returning the TransferResponseDto
    }


    @Override
    public List<FundTransfer> getTransactionsByCustomer(Long userId, Map<String, String> queryParams) {
        return List.of();
    }

    @Override
    public FundTransfer getTransactionById(Long transactionId) {
        return null;
    }

    @Override
    public boolean validateTransactionLimit(Long accountId, BigDecimal amount) {
        return false;
    }

    @Override
    public void setupScheduledTransfer(TransferRequestDto requestDto, LocalDateTime scheduleDate) {

    }

    @Override
    public void setupRecurringTransfer(TransferRequestDto requestDto, String frequency) {

    }

    @Override
    public void cancelScheduledTransfer(Long paymentId) {

    }

    @Override
    public void cancelRecurringTransfer(Long paymentId) {

    }
}
