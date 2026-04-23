package test;
import main.BankAccount;
import main.AdminService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminServiceTest {

    private BankAccount account;
    private AdminService admin;

    @BeforeEach
    public void setUp() {
        account = new BankAccount();
        admin = new AdminService();
    }

    @Test
    public void testCollectFeeReducesBalance() {
        account.deposit(100);
        admin.collectFee(account, 10);
        assertEquals(90, account.getBalance(), 0.01);
    }
    
}
