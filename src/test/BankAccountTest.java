package test;

import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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

    // Tests for Task 2: Withdraw
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


}
