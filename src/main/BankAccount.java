package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.Loan;

public class BankAccount {

    private double balance;
    private final List<Transaction> transactionHistory;
    private Loan loan;

    public BankAccount() {
        this.balance = 0;
        transactionHistory = new ArrayList<>();
        loan = null;
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
            throw new IllegalStateException("Insufficient funds");
        }
        else {
            this.balance -= amount;
            transactionHistory.add(new Transaction(Transaction.Type.WITHDRAWAL, amount, balance));
        }
    }

    public void applyForLoan(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than zero.");
        }
        if (loan == null) {
            loan = new Loan(amount);
        }
        else {
            loan.addAmount(amount);
        }
        balance += amount;
    }

    public void makeLoanPayment(double amount) {
        if (loan == null || loan.getLoanBalance() == 0) {
            throw new IllegalStateException("No outstanding loan to pay.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds.");
        }
        loan.makePayment(amount);
        balance -= amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getLoanBalance() {
        return loan == null ? 0 : loan.getLoanBalance();
    }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

}
