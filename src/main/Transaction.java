package main;

public class Transaction {

    public enum Type {
        DEPOSIT,
        WITHDRAWAL
    }

    private final Type type;
    private final double amount;
    private final double balanceAfter;
    
    public Transaction(Type type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    @Override
    public String toString() {
        return String.format("%-10s $%8.2f Balance: $%.2f", type, amount, balanceAfter);
    }
}
