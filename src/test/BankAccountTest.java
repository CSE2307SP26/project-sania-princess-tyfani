package test;

import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.beans.Transient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    private BankAccount account;
    
    @BeforeEach
    public void setUp() {
        account = new BankAccount();
    }

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    // Tests for Withdraw
    @Test
    public void testWithdrawReducesBalance() {
        account.deposit(100);
        account.withdraw(40);
        assertEquals(60, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        account.deposit(100);
        try {
        account.withdraw(200);
        fail();
        } catch (IllegalStateException e) {
            // expected
        }
    }

    // Tests for View Transaction History
    @Test
    public void testHistoryEmptyOnNewAccount() {
        assertEquals(0, account.getTransactionHistory().size());
    }

    @Test
    public void testHistoryRecordsDeposit() {
        account.deposit(100);
        assertEquals(1, account.getTransactionHistory().size());
        assertEquals(Transaction.Type.DEPOSIT, account.getTransactionHistory().get(0).getType());
        assertEquals(100, account.getTransactionHistory().get(0).getAmount(), 0.01);
        assertEquals(100, account.getTransactionHistory().get(0).getBalanceAfter(), 0.01);
    }

    @Test
    public void testHistoryRecordsWithdrawal() {
        account.deposit(100);
        account.withdraw(40);
        assertEquals(2, account.getTransactionHistory().size());
        assertEquals(Transaction.Type.WITHDRAWAL, account.getTransactionHistory().get(1).getType());
        assertEquals(40, account.getTransactionHistory().get(1).getAmount(), 0.01);
        assertEquals(60, account.getTransactionHistory().get(1).getBalanceAfter(), 0.01);
    }

    @Test
    public void testHistoryMultipleTransactionsInOrder() {
        account.deposit(200);
        account.withdraw(50);
        account.deposit(25);
        assertEquals(Transaction.Type.DEPOSIT, account.getTransactionHistory().get(0).getType());
        assertEquals(Transaction.Type.WITHDRAWAL, account.getTransactionHistory().get(1).getType());
        assertEquals(Transaction.Type.DEPOSIT, account.getTransactionHistory().get(2).getType());
    }

    @Test
    public void testHistoryFailedWithdrawalNotRecorded() {
        account.deposit(50);
        try {
            account.withdraw(200);
        } catch (IllegalStateException e) {
            // expected
        }
        assertEquals(1, account.getTransactionHistory().size());
    }

    // Tests for Loan Payment (interact with account balance so kept here)

    @Test
    public void testLoanPaymentReducesAccountBalance() {
        account.applyForLoan(500);
        account.makeLoanPayment(100);
        assertEquals(400, account.getBalance(), 0.01);
    }

    @Test 
    public void testLoanPaymentMoreThanAccountBalanceThrowsException() {
        account.applyForLoan(500);
        account.withdraw(400);
        try {
            account.makeLoanPayment(200);
            fail("Expected IllegalStateException for insufficient funds");
        } catch (IllegalStateException e) {
            // expected
        }
    }

}


