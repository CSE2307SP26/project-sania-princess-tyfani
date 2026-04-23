package main;

import java.util.List;
import java.util.Scanner;

import main.AdminService;

public class MainMenu {

    private static final int DEPOSIT_SELECTION = 1;
    private static final int WITHDRAW_SELECTION = 2;
    private static final int HISTORY_SELECTION = 3;
    private static final int LOAN_SELECTION = 4;
    private static final int LOAN_PAYMENT_SELECTION = 5;
    private static final int COLLECT_FEE_SELECTION = 6;
    private static final int EXIT_SELECTION = 7;
	private static final int MAX_SELECTION = 7;

	private BankAccount userAccount;
    private Scanner keyboardInput;

    public MainMenu() {
        this.userAccount = new BankAccount();
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. View transaction history");
        System.out.println("4. Apply for a loan");
        System.out.println("5. Make a loan payment");
        System.out.println("6. Collect a fee (admin)");
        System.out.println("7. Exit the app");

    }

    public int getUserSelection(int max) {
        int selection = -1;
        while(selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }

    public void processInput(int selection) {
        switch (selection) {
            case 1:
                performDeposit();
                break;
            case 2:
                performWithdrawal();
                break;
            case 3:
                displayTransactionHistory();
                break;
            case 4:
                performLoanApplication();
                break;
            case 5:
                performLoanPayment();
                break;
            case 6:
                performCollectFee();
                break;
        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while(depositAmount <= 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        try {
            userAccount.deposit(depositAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid deposit amount");
        }
    }

    public void performWithdrawal() {
        double withdrawalAmount = -1;
        while(withdrawalAmount <= 0) {
            System.out.print("How much would you like to withdraw: ");
            withdrawalAmount = keyboardInput.nextInt();
        }
        try {
            userAccount.withdraw(withdrawalAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Insufficient funds");
        }
    }

    public void displayTransactionHistory() {
        List<Transaction> history = userAccount.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transactions yet");
            return;
        }
        else {
            System.out.println("--Transaction History--");
            for (int i = 0; i < history.size(); i++) {
                System.out.printf("%3d. %s%n", i + 1, history.get(i));
            }
            System.out.println("-----");
        }
    }

    public void performLoanApplication() {
        double loanAmount = 0;
        while (loanAmount <= 0) {
            System.out.print("How much would you like to borrow: ");
            if (keyboardInput.hasNextDouble()) {
                loanAmount = keyboardInput.nextDouble();
                if (loanAmount <= 0) {
                    System.out.println("Please enter an amount greater than zero");
                }
            }
            else {
                System.out.println("Invalid input. Please enter a number.");
                keyboardInput.next();
            }
        }
        try {
            userAccount.applyForLoan(loanAmount);
            System.out.printf("Loan of $%.2f approved. New balance: $%.2f. Outstanding loan: $%.2f%n", loanAmount, userAccount.getBalance(), userAccount.getLoanBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void performLoanPayment() {
        if (userAccount.getLoanBalance() == 0) {
            System.out.println("You have no outstanding loan.");
            return;
        }
        System.out.printf("Outstanding loan balance: $%.2f%n", userAccount.getLoanBalance());
        double paymentAmount = 0;
        while (paymentAmount <= 0) {
            System.out.print("How much would you like to pay: ");
            if (keyboardInput.hasNextDouble()) {
                paymentAmount = keyboardInput.nextDouble();
                if (paymentAmount <= 0) {
                    System.out.println("Please enter an amount greater than zero");
                }
            }
            else {
                System.out.println("Invalid input. Please enter a number.");
                keyboardInput.next();
            }
        }
        try {
            userAccount.makeLoanPayment(paymentAmount);
            System.out.printf("Payment of $%.2f accepted. Remaining loan: $%.2f%n", paymentAmount, userAccount.getLoanBalance());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    

    public void run() {
        int selection = -1;
        while(selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }

    public void performCollectFee() {
        AdminService admin = new AdminService();
        double feeAmount = 0;
        while (feeAmount <= 0){
            System.out.print("Enter fee amount to collect: ");
            if (keyboardInput.hasNextDouble()) {
                feeAmount = keyboardInput.nextDouble();
            }
            else {
                System.out.println("Invalid input. Please enter a number");
                keyboardInput.next();
            }
        }
        try {
            admin.collectFee(userAccount, feeAmount);
            System.out.printf("Fee of $%.2f collected. New balance: $%.2f%n", feeAmount, userAccount.getBalance());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
