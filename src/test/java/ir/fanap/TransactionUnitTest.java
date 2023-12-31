package ir.fanap;

import ir.fanap.bankingsystem.BankAccount;
import ir.fanap.bankingsystem.Transaction;
import ir.fanap.bankingsystem.accounttype.BasicAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class TransactionUnitTest {

    private static long id = 1;

    @Test
    void testTransactionExecute_Success() {
        double amount = 5000;
        double balance = 10_000;
        BankAccount account = new BasicAccount(1, "Ali", balance);
        Transaction transaction = new Transaction(id++, Transaction.DEPO, amount, LocalDateTime.now(), account);
        transaction.execute();

        Assertions.assertEquals(balance + amount, transaction.getAccount().getBalance());
    }

    @Test
    void testTransactionRollback() {
        double amount = 5000;
        double balance = 10_000;
        BankAccount account = new BasicAccount(1, "Ali", balance);
        Transaction transaction = new Transaction(id++, Transaction.WITHDRAW, amount, LocalDateTime.now(), account);
        transaction.execute();
        transaction.rollback();

        Assertions.assertEquals(balance, transaction.getAccount().getBalance());
    }
}
