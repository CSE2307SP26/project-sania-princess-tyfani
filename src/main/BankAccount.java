package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {

    private double balance;
    private final List<Transaction> transactionHistory;
    private double loanBalance;

    public BankAccount() {
        this.balance = 0;
        transactionHistory = new ArrayList<>();
        loanBalance = 0;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
            transactionHistory.add(new Transaction(Transaction.Type.DEPOSIT, amount, balance));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void withdraw(double amount) {
        if (amount > this.balance) {
            throw new IllegalArgumentException();
        }
        else {
            this.balance -= amount;
            transactionHistory.add(new Transaction(Transaction.Type.WITHDRAWAL, amount, balance));
        }
    }

    public void applyForLoan(double amount) {
        if (amount > 0) {
            loanBalance += amount;
            balance += amount;
        }
        else {
            throw new IllegalArgumentException("Loan amount must be greater than zero.");
        }
    }

    public void makeLoanPayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment must be greater than zero.");
        }
        if (loanBalance == 0) {
            throw new IllegalStateException("No outstanding loan to pay.");
        }
        if (amount > loanBalance) {
            throw new IllegalArgumentException("Payment exceeds outstanding loan balance.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds.");
        }
        loanBalance -= amount;
        balance -= amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getLoanBalance() {
        return loanBalance;
    }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

}
