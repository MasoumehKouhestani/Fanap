package ir.fanap.bankingsystem.accounttype;

import ir.fanap.bankingsystem.BankAccount;
import ir.fanap.bankingsystem.exception.DepositionFailureException;
import ir.fanap.bankingsystem.exception.WithdrawalFailureException;

/**
 * - Simple operations <br>
 * - Fewer limitations for transactions ---> I did not understand this one actually!
 */
public class BasicAccount extends BankAccount {

    public static final double DEPOSIT_LIMIT = 1_000_000_000D;

    public BasicAccount(long accountNumber, String ownerName, double balance) {
        super(accountNumber, ownerName, balance);
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
        if (this.balance > DEPOSIT_LIMIT) {
            throw new DepositionFailureException();
        }
    }

    @Override
    public void withdraw(double amount) {
        this.balance -= amount;
        if (this.balance < 0) {
            throw new WithdrawalFailureException();
        }
    }

}
