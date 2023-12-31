package ir.fanap.accounttype;

import ir.fanap.bankingsystem.BankAccount;
import ir.fanap.bankingsystem.accounttype.BasicAccount;
import ir.fanap.bankingsystem.exception.DepositionFailureException;
import ir.fanap.bankingsystem.exception.WithdrawalFailureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicAccountUnitTest {

    private static final long ACCOUNT_NUMBER = 1L;
    private static final String OWNER_NAME = "Ali";
    private static final double BALANCE = 1000_000D;

    @Test
    void testDepositFromBasicAccount_Success() {
        BankAccount account = new BasicAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        double amount = 5000;
        account.deposit(amount);
        Assertions.assertEquals(BALANCE + amount, account.getBalance());
    }

    @Test
    void testDepositFromBasicAccount_Failure() {
        BankAccount account = new BasicAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        Assertions.assertThrows(DepositionFailureException.class, () -> account.deposit(BasicAccount.DEPOSIT_LIMIT));
    }

    @Test
    void testWithdrawFromBasicAccount_Success() {
        BankAccount account = new BasicAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        double amount = 5000;
        account.withdraw(amount);
        Assertions.assertEquals(BALANCE - amount, account.getBalance());
    }

    @Test
    void testWithdrawFromBasicAccount_Failure() {
        BankAccount account = new BasicAccount(ACCOUNT_NUMBER, OWNER_NAME, BALANCE);
        Assertions.assertThrows(WithdrawalFailureException.class, () -> account.withdraw(2 * BALANCE));
    }
}
