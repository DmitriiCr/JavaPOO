package com.example.bank.controllers;

import com.example.bank.constants.ACTION;
import com.example.bank.entity.Account;
import com.example.bank.services.AccountService;
import com.example.bank.services.TransactionService;
import com.example.bank.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.example.bank.constants.Constants.*;

@RestController
@RequestMapping("api/v1")
public class TransactionRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRestController.class);

    private final AccountService accountService;
    private final TransactionService transactionService;

    @Autowired
    public TransactionRestController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/transactions")
    public ResponseEntity<?> makeTransfer(
            @Valid @RequestBody TransactionDTO transactionDTO) {
        if (InputValidator.isSearchTransactionValid(transactionDTO)) {
//            new Thread(() -> transactionService.makeTransfer(transactionInput));
            boolean isComplete = transactionService.makeTransfer(transactionDTO);
            return new ResponseEntity<>(isComplete, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(INVALID_TRANSACTION, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/withdraw")
    public ResponseEntity<?> withdraw(
            @Valid @RequestBody WithdrawDTO withdrawDTO) {
        LOGGER.debug("Triggered AccountRestController.withdrawInput");

        // Validate input
        if (InputValidator.isSearchCriteriaValid(withdrawDTO)) {
            // Attempt to retrieve the account information
            Account account = accountService.getAccount(
                    withdrawDTO.getSortCode(), withdrawDTO.getAccountNumber());

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                if (transactionService.isAmountAvailable(withdrawDTO.getAmount(), account.getCurrentBalance())) {
                    transactionService.updateAccountBalance(account, withdrawDTO.getAmount(), ACTION.WITHDRAW);
                    return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                }
                return new ResponseEntity<>(INSUFFICIENT_ACCOUNT_BALANCE, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/deposit")
    public ResponseEntity<?> deposit(
            @Valid @RequestBody DepositDTO depositDTO) {
        LOGGER.debug("Triggered AccountRestController.depositInput");

        // Validate input
        if (InputValidator.isAccountNoValid(depositDTO.getTargetAccountNo())) {
            // Attempt to retrieve the account information
            Account account = accountService.getAccount(depositDTO.getTargetAccountNo());

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                transactionService.updateAccountBalance(account, depositDTO.getAmount(), ACTION.DEPOSIT);
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }

    //Intercepting fields with bad syntax and showing to user in json format
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
