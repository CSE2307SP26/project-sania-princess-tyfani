package test;

import main.Loan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoanTest {

    private Loan loan;

    @BeforeEach
    public void setUp() {
        loan = new Loan(500);
    }

    @Test
    public void testInitialLoanBalance() {
        assertEquals(500, loan.getLoanBalance(), 0.01);
    }

    @Test
    public void testMakePaymentReducesBalance() {
        loan.makePayment(100);
        assertEquals(400, loan.getLoanBalance(), 0.01);
    }

    @Test
    public void testFullPayoff() {
        loan.makePayment(500);
        assertEquals(0, loan.getLoanBalance(), 0.01);
    }

    @Test 
    public void testPaymentExceedsBalanceThrowsException() {
        try {
            loan.makePayment(600);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test 
    public void testZeroPaymentThrowsException() {
        try {
            loan.makePayment(0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test 
    public void testNegativeLoanAmountThrowsException() {
        try {
            new Loan(-100);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
    
    @Test 
    public void testZeroLoanAmountThrowsException() {
        try {
            new Loan(0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test 
    public void testAddAmountIncreasesBalance() {
        loan.addAmount(200);
        assertEquals(700, loan.getLoanBalance(), 0.01);
    }

    @Test 
    public void testAddNegativeAmountThrowsException() {
        try {
            loan.addAmount(-100);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
