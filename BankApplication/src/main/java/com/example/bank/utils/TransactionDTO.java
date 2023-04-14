package com.example.bank.utils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

public class TransactionDTO {

    private AccountDTO sourceAccount;

    private AccountDTO targetAccount;

    @Positive(message = "Transfer amount must be positive")
    // Prevent fraudulent transfers attempting to abuse currency conversion errors
    @Min(value = 1, message = "Amount must be larger than 1")
    private double amount;
    public TransactionDTO() {}

    public AccountDTO getSourceAccount() {
        return sourceAccount;
    }
    public void setSourceAccount(AccountDTO sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
    public AccountDTO getTargetAccount() {
        return targetAccount;
    }
    public void setTargetAccount(AccountDTO targetAccount) {
        this.targetAccount = targetAccount;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionInput{" +
                "sourceAccount=" + sourceAccount +
                ", targetAccount=" + targetAccount +
                ", amount=" + amount +
                '}';
    }
}
