package ir.fanap.accounttype;

import ir.fanap.bankingsystem.BankAccount;
import ir.fanap.bankingsystem.accounttype.BusinessAccount;
import ir.fanap.bankingsystem.exception.DepositionFailureException;
import ir.fanap.bankingsystem.exception.WithdrawalFailureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BusinessAccountUnitTest {


    private static final long ACCOUNT_NUMBER = 1L;
    private static final String OWNER_NAME = "Ali";
    private static final double BALANCE = 9_000_000_000D;

    @Test
    void testDepositFromBusinessAccount_Success() {
        BankAccount account = new BusinessAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        double amount = 5000;
        account.deposit(amount);
        Assertions.assertEquals(BALANCE + amount, account.getBalance());
    }

    @Test
    void testDepositFromBusinessAccount_Failure() {
        BankAccount account = new BusinessAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        Assertions.assertThrows(DepositionFailureException.class, () -> account.deposit(BusinessAccount.DEPOSIT_LIMIT));
    }

    @Test
    void testWithdrawFromBusinessAccount_Success() {
        BankAccount account = new BusinessAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        double amount = 2_000_000_000;
        account.withdraw(amount);
        Assertions.assertEquals(BALANCE - amount - (amount * BusinessAccount.COST), account.getBalance());
    }

    @Test
    void testWithdrawFromBusinessAccount_Failure() {
        BankAccount account = new BusinessAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        Assertions.assertThrows(WithdrawalFailureException.class, () -> account.withdraw(2 * BALANCE));
    }
}
