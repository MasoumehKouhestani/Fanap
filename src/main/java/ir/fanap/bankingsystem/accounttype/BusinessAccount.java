package ir.fanap.bankingsystem.accounttype;

import ir.fanap.bankingsystem.BankAccount;
import ir.fanap.bankingsystem.exception.DepositionFailureException;
import ir.fanap.bankingsystem.exception.WithdrawalFailureException;

/**
 * - Higher balance limit and business features <br>
 * - Variable transaction costs for bigger operations
 */
public class BusinessAccount extends BankAccount {

    public static final double TRANSACTION_COST_LIMIT = 1_000_000_000D;
    public static final double DEPOSIT_LIMIT = 100_000_000_000D;
    public static final double COST = 0.1;

    public BusinessAccount(long accountNumber, String ownerName, double balance) {
        super(accountNumber, ownerName, balance);
    }

    @Override
    public void deposit(double amount) {
        if (balance + amount > DEPOSIT_LIMIT) {
            throw new DepositionFailureException();
        }
        withdrawTransactionCost(amount);
        this.balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (this.balance - amount < amount * COST) {
            throw new WithdrawalFailureException();
        }
        withdrawTransactionCost(amount);
        this.balance -= amount;
    }

    private void withdrawTransactionCost(double amount) {
        if (amount >= TRANSACTION_COST_LIMIT) {
            this.balance -= (amount * COST);
        }
    }

}
