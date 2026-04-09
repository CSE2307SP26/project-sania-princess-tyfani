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

    public void testWithdrawInsufficientFunds() {
        account.deposit(100);
        try {
        account.withdraw(200);
        fail();
        } catch (IllegalArgumentException e) {

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

    // Tests for Apply Loan
    @Test
    public void testApplyLoanIncreasesBalance() {
        account.applyForLoan(500);
        assertEquals(500, account.getBalance(), 0.01);
    }

    @Test
    public void testApplyLoanTracksLoanBalance() {
        account.applyForLoan(500);
        assertEquals(500, account.getLoanBalance(), 0.01);
    }

    @Test
    public void testApplyLoanMultipleLoansAddTogether() {
        account.applyForLoan(500);
        account.applyForLoan(200);
        assertEquals(700, account.getLoanBalance(), 0.01);
    }

    @Test 
    public void testApplyLoanZeroThrowsException() {
        try {
            account.applyForLoan(0);
            fail("Expected IllegalArgumentException for zero loan");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testApplyForLoanNegativeThrowsException() {
        try {
            account.applyForLoan(-100);
            fail("Expected IllegalArgumentsException for negative loan");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    // Tests for Loan Payment

    @Test
    public void testLoanPaymentReducesLoanBalance() {
        account.applyForLoan(500);
        account.makeLoanPayment(100);
        assertEquals(400, account.getBalance(), 0.01);
    }

    @Test
    public void testLoanPaymentReducesAccountBalance() {
        account.applyForLoan(500);
        account.makeLoanPayment(100);
        assertEquals(400, account.getBalance(), 0.01);
    }

    @Test
    public void testLoanPaymentFullPayoff() {
        account.applyForLoan(500);
        account.makeLoanPayment(500);
        assertEquals(0, account.getLoanBalance(), 0.01);
    }

    @Test 
    public void testLoanPaymentMoreThanLoanBalanceThrowsException() {
        account.applyForLoan(500);
        try {
            account.makeLoanPayment(600);
            fail("Expected IllegalArgumentException for payment exceeding loan balance");
        } catch (IllegalArgumentException e) {
            // expected
        }
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

    @Test 
    public void testLoanPaymentNoLoanThrowsException() {
        try {
            account.makeLoanPayment(100);
            fail("Expected IllegalStateException for no outstanding loan");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    @Test 
    public void testLoanPaymentZeroThrowsException() {
        account.applyForLoan(500);
        try {
           account.makeLoanPayment(0);
           fail("Expected IllegalArgumentException for zero payment");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

}


