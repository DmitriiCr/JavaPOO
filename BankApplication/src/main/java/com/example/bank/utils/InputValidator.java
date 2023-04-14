package com.example.bank.utils;

import com.example.bank.constants.Constants;

// Methods for checking validity of data pattern
public class InputValidator {

    public static boolean isSearchCriteriaValid(AccountDTO accountInput) {
        return Constants.SORT_CODE_PATTERN.matcher(accountInput.getSortCode()).find() &&
                Constants.ACCOUNT_NUMBER_PATTERN.matcher(accountInput.getAccountNumber()).find();
    }

    public static boolean isAccountNoValid(String accountNo) {
        return Constants.ACCOUNT_NUMBER_PATTERN.matcher(accountNo).find();
    }

    public static boolean isCreateAccountCriteriaValid(CreateAccountDTO createAccountDTO) {
        return (!createAccountDTO.getBankName().isBlank() && !createAccountDTO.getOwnerName().isBlank());
    }

    public static boolean isSearchTransactionValid(TransactionDTO transactionDTO) {
        // checks for large amounts; consider past history of account holder and location of transfers

        if (!isSearchCriteriaValid(transactionDTO.getSourceAccount()))
            return false;

        if (!isSearchCriteriaValid(transactionDTO.getTargetAccount()))
            return false;

        if (transactionDTO.getSourceAccount().equals(transactionDTO.getTargetAccount()))
            return false;

        return true;
    }
}
