package main;

public class AdminService {

    public void collectFee(BankAccount account, double feeAmount) {
        if (feeAmount <= 0) {
            throw new IllegalArgumentException("Fee amount must be greater than zero.");
        }
        account.applyFee(feeAmount);
    }
    
}
