package com.example.bank.controllers;

import com.example.bank.constants.Constants;
import com.example.bank.entity.Account;
import com.example.bank.services.AccountService;
import com.example.bank.utils.AccountDTO;
import com.example.bank.utils.CreateAccountDTO;
import com.example.bank.utils.InputValidator;
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

@RestController
@RequestMapping("api/v1")
public class AccountRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);

    private final AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/get-accounts")
    public ResponseEntity<?> allAccounts(){
        return new ResponseEntity<>(accountService.findAllAccounts(), HttpStatus.OK);
    }
    @PostMapping(value = "/accounts")
    public ResponseEntity<?> checkAccountBalance(
            @Valid @RequestBody AccountDTO accountDTO) {
        LOGGER.debug("Triggered AccountRestController.accountInput");

        // Validate input
        if (InputValidator.isSearchCriteriaValid(accountDTO)) {
            // Attempt to retrieve the account information
            Account account = accountService.getAccount(
                    accountDTO.getSortCode(), accountDTO.getAccountNumber());

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity<>(Constants.NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(Constants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/accounts")
    public ResponseEntity<?> createAccount(
            @Valid @RequestBody CreateAccountDTO createAccountDTO) {
        LOGGER.debug("Triggered AccountRestController.createAccountInput");

        // Validate input
        if (InputValidator.isCreateAccountCriteriaValid(createAccountDTO)) {
            // Attempt to retrieve the account information
            Account account = accountService.createAccount(
                    createAccountDTO.getBankName(), createAccountDTO.getOwnerName());

            // Return the account details, or warn that no account was found for given input
            if (account == null) {
                return new ResponseEntity<>(Constants.CREATE_ACCOUNT_FAILED, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(Constants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }

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
