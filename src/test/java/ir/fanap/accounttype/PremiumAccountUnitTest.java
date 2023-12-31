package ir.fanap.accounttype;

import ir.fanap.bankingsystem.BankAccount;
import ir.fanap.bankingsystem.accounttype.PremiumAccount;
import ir.fanap.bankingsystem.exception.DepositionFailureException;
import ir.fanap.bankingsystem.exception.WithdrawalFailureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PremiumAccountUnitTest {


    private static final long ACCOUNT_NUMBER = 1L;
    private static final String OWNER_NAME = "Ali";
    private static final double BALANCE = 1000_000D;

    @Test
    void testDepositFromPremiumAccount_Success() {
        BankAccount account = new PremiumAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        double amount = 5000;
        account.deposit(amount);
        Assertions.assertEquals(BALANCE + amount - PremiumAccount.INTEREST_RATE, account.getBalance());
    }

    @Test
    void testDepositFromPremiumAccount_Failure() {
        BankAccount account = new PremiumAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        Assertions.assertThrows(DepositionFailureException.class, () -> account.deposit(PremiumAccount.DEPOSIT_LIMIT));
    }

    @Test
    void testWithdrawFromPremiumAccount_Success() {
        BankAccount account = new PremiumAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        double amount = 5000;
        account.withdraw(amount);
        Assertions.assertEquals(BALANCE - amount - PremiumAccount.INTEREST_RATE, account.getBalance());
    }

    @Test
    void testWithdrawFromPremiumAccount_Failure() {
        BankAccount account = new PremiumAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        Assertions.assertThrows(WithdrawalFailureException.class, () -> account.withdraw(2 * BALANCE));
    }

    @Test
    void testLoanFromPremiumAccount_Success() {
        PremiumAccount account = new PremiumAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        double amount = 5000;
        account.loan(amount);
        Assertions.assertEquals(BALANCE + amount - PremiumAccount.INTEREST_RATE, account.getBalance());
    }

    @Test
    void testLoanFromPremiumAccount_Failure() {
        PremiumAccount account = new PremiumAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        Assertions.assertThrows(DepositionFailureException.class, () -> account.loan(PremiumAccount.DEPOSIT_LIMIT));
    }

    @Test
    void testInvestFromPremiumAccount_Success() {
        PremiumAccount account = new PremiumAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        double amount = 5000;
        account.invest(amount);
        Assertions.assertEquals(BALANCE - amount - PremiumAccount.INTEREST_RATE, account.getBalance());
    }

    @Test
    void testInvestFromPremiumAccount_Failure() {
        PremiumAccount account = new PremiumAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        Assertions.assertThrows(WithdrawalFailureException.class, () -> account.invest(2 * BALANCE));
    }
}
