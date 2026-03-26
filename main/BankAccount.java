package main;
import java.util.Scanner;


public class BankAccount {

    private double balance;

    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public double makeTransfer(double amountToTransfer, BankAccount alternative){
        System.err.println(balance);
        if(balance > amountToTransfer){
            balance = balance - amountToTransfer;
            alternative.deposit(amountToTransfer);
        }else{
           System.err.println("INSUFFICIENT FUNDS"); 
        }
    }

    public void closeAccount(BankAccount choiceAcc){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to close this account: Y/N");
        int selection = scanner.nextInt();
        if(selection = 'Y'){
             Scanner scanner2 = new Scanner(System.in);
             System.out.println("Would you like to make a withdrawal: Y/N");
             if(selection = 'Y'){
                System.err.println("Withdraw All Funds")
             }else{
                System.err.println("nevermind");
             }
        }
    }
}