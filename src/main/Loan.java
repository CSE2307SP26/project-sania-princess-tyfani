package main;

public class Loan {
    private double loanBalance;

    public Loan(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than zero.");
        }
        this.loanBalance = amount;
    }

    public void addAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Loan must be greater than zero.");
        }
        loanBalance += amount;
    }

    public void makePayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment must be greater than zero.")
        }
        if (loanBalance == 0) {
            throw new IllegalStateException("No outstanding loan to pay.");
        }
        if (amount > loanBalance) {
            throw new IllegalArgumentException("Payment exceeds outstanding loan balance.");
        }
        loanBalance -= amount;
    }

    public double getLoanBalance() {
        return loanBalance;
    }
    
}
