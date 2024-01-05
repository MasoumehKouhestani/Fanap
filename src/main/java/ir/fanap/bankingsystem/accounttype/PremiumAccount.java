package ir.fanap.bankingsystem.accounttype;

import ir.fanap.bankingsystem.BankAccount;
import ir.fanap.bankingsystem.exception.DepositionFailureException;
import ir.fanap.bankingsystem.exception.WithdrawalFailureException;
import ir.fanap.bankingsystem.features.InvestmentCapable;
import ir.fanap.bankingsystem.features.Loanable;

import java.util.Random;

/**
 * - Interest rate for balance <br>
 * - Features like investing
 */
public class PremiumAccount extends BankAccount implements InvestmentCapable, Loanable {

    public static final double INTEREST_RATE = 1000D;
    public static final double DEPOSIT_LIMIT = 1_000_000_000D;

    public PremiumAccount(long accountNumber, String ownerName, double balance) {
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
        if (this.balance <= INTEREST_RATE) {
            throw new WithdrawalFailureException();
        }
    }

    @Override
    public double getBalance() {
        this.balance -= INTEREST_RATE;
        return this.balance;
    }

    @Override
    public void invest(double amount) {
        withdraw(amount);
        Random random = new Random();
        System.out.printf("You have invested on Company No.%d value of %f dollars.\n", random.nextInt(), amount);
    }

    @Override
    public void loan(double amount) {
        deposit(amount);
        System.out.printf("You received a bank loan of %f dollars.\n", amount);
    }
}
