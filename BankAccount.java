package main;
import java.util.ArrayList;

public class BankAccount {

    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount() {
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

  public void deposit(double amount) {
    if(amount > 0) {
        this.balance += amount;

        transactionHistory.add(
            String.format("Deposited: $%.2f | Balance: $%.2f", amount, balance)
        );

    } else {
        throw new IllegalArgumentException();
    }
}

    public double getBalance() {
        return this.balance;
    }

    //Create Method to Fully Flesh Out View Activity
     public void printTransactionHistory() {
        System.out.println("\n=== Activity History ===");
        System.out.println("Activity check works!");
        System.out.println("========================\n");
    }
}

