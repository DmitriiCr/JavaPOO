package com.example.bank.services;

import com.example.bank.constants.ACTION;
import com.example.bank.entity.Account;
import com.example.bank.entity.Transaction;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.TransactionRepository;
import com.example.bank.utils.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public boolean makeTransfer(TransactionDTO transactionDTO) {
        String sourceSortCode = transactionDTO.getSourceAccount().getSortCode();
        String sourceAccountNumber = transactionDTO.getSourceAccount().getAccountNumber();
        Optional<Account> sourceAccount = accountRepository
                .findBySortCodeAndAccountNumber(sourceSortCode, sourceAccountNumber);

        String targetSortCode = transactionDTO.getTargetAccount().getSortCode();
        String targetAccountNumber = transactionDTO.getTargetAccount().getAccountNumber();
        Optional<Account> targetAccount = accountRepository
                .findBySortCodeAndAccountNumber(targetSortCode, targetAccountNumber);

        if (sourceAccount.isPresent() && targetAccount.isPresent()) {
            if (isAmountAvailable(transactionDTO.getAmount(), sourceAccount.get().getCurrentBalance())) {
                var transaction = new Transaction();

                transaction.setAmount(transactionDTO.getAmount());
                transaction.setSourceAccountId(sourceAccount.get().getId());
                transaction.setTargetAccountId(targetAccount.get().getId());
                transaction.setTargetOwnerName(targetAccount.get().getOwnerName());
                transaction.setInitiationDate(LocalDateTime.now());

                updateAccountBalance(sourceAccount.get(), transactionDTO.getAmount(), ACTION.WITHDRAW);
                transactionRepository.save(transaction);

                return true;
            }
        }
        return false;
    }

     public void updateAccountBalance(Account account, double amount, ACTION action) {
        if (action == ACTION.WITHDRAW) {
            account.setCurrentBalance((account.getCurrentBalance() - amount));
        } else if (action == ACTION.DEPOSIT) {
            account.setCurrentBalance((account.getCurrentBalance() + amount));
        }
        accountRepository.save(account);
    }

    public boolean isAmountAvailable(double amount, double accountBalance) {
        return (accountBalance - amount) > 0;
    }
}
