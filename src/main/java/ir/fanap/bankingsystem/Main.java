package ir.fanap.bankingsystem;

import ir.fanap.bankingsystem.accounttype.BasicAccount;
import ir.fanap.bankingsystem.accounttype.BusinessAccount;
import ir.fanap.bankingsystem.accounttype.PremiumAccount;
import ir.fanap.bankingsystem.exception.DepositionFailureException;
import ir.fanap.bankingsystem.exception.WithdrawalFailureException;

import java.time.LocalDateTime;

public class Main {

    private static long transactionId = 1L;

    public static void main(String[] args) {
        BankAccount basicAccount = new BasicAccount(1L, "Ali", 5_000);
        BankAccount premiumAccount = new PremiumAccount(2L, "Taghi", 1_000_000);
        BankAccount businessAccount = new BusinessAccount(3L, "Naghi", 1_001_000_000);


        /* From premiumAccount -> basicAccount amount of: 5_000 */
        double amount1 = 5_000;
        Transaction transaction1 = new Transaction(transactionId++, Transaction.WITHDRAW, amount1, LocalDateTime.now(), premiumAccount);
        Transaction transaction2 = new Transaction(transactionId++, Transaction.DEPO, amount1, LocalDateTime.now(), basicAccount);
        System.out.printf("Balance of %s is %f.\n", premiumAccount.getOwnerName(), premiumAccount.getBalance());
        System.out.printf("Balance of %s is %f.\n", basicAccount.getOwnerName(), basicAccount.getBalance());
        transaction1.execute();
        transaction2.execute();
        System.out.printf("From %s to %s amount of %f.\n", premiumAccount.getOwnerName(), basicAccount.getOwnerName(), amount1);
        System.out.printf("Balance of %s is %f.\n", premiumAccount.getOwnerName(), premiumAccount.getBalance());
        System.out.printf("Balance of %s is %f.\n", basicAccount.getOwnerName(), basicAccount.getBalance());
        System.out.println("---------------------------------------------------------------");

        /* From businessAccount -> premiumAccount amount of: 1_000_000 */
        double amount2 = 1_000_000;
        Transaction transaction3 = new Transaction(transactionId++, Transaction.WITHDRAW, amount2, LocalDateTime.now(), businessAccount);
        Transaction transaction4 = new Transaction(transactionId++, Transaction.DEPO, amount2, LocalDateTime.now(), premiumAccount);
        System.out.printf("Balance of %s is %f.\n", businessAccount.getOwnerName(), businessAccount.getBalance());
        System.out.printf("Balance of %s is %f.\n", premiumAccount.getOwnerName(), premiumAccount.getBalance());
        transaction3.execute();
        transaction4.execute();
        System.out.printf("From %s to %s amount of %f.\n", businessAccount.getOwnerName(), premiumAccount.getOwnerName(), amount2);
        System.out.printf("Balance of %s is %f.\n", businessAccount.getOwnerName(), businessAccount.getBalance());
        System.out.printf("Balance of %s is %f.\n", premiumAccount.getOwnerName(), premiumAccount.getBalance());
        System.out.println("---------------------------------------------------------------");

        /* From premiumAccount invest amount of: 500_000 */
        double amount3 = 500_000;
        Transaction transaction5 = new Transaction(transactionId++, Transaction.INVEST, amount3, LocalDateTime.now(), premiumAccount);
        System.out.printf("Balance of %s is %f.\n", premiumAccount.getOwnerName(), premiumAccount.getBalance());
        transaction5.execute();
        System.out.printf("invest from %s amount of %f.\n", premiumAccount.getOwnerName(), amount3);
        System.out.printf("Balance of %s is %f.\n", premiumAccount.getOwnerName(), premiumAccount.getBalance());
        System.out.println("---------------------------------------------------------------");

        /* From premiumAccount loan amount of: 500_000 */
        Transaction transaction6 = new Transaction(transactionId++, Transaction.LOAN, amount3, LocalDateTime.now(), premiumAccount);
        System.out.printf("Balance of %s is %f.\n", premiumAccount.getOwnerName(), premiumAccount.getBalance());
        transaction6.execute();
        System.out.printf("loan from %s amount of %f.\n", premiumAccount.getOwnerName(), amount3);
        System.out.printf("Balance of %s is %f.\n", premiumAccount.getOwnerName(), premiumAccount.getBalance());
        System.out.println("---------------------------------------------------------------");

        /* From businessAccount loan amount of: 500_000 -> ERROR */
        Transaction transaction7 = new Transaction(transactionId++, Transaction.LOAN, amount3, LocalDateTime.now(), businessAccount);
        System.out.printf("Balance of %s is %f.\n", businessAccount.getOwnerName(), businessAccount.getBalance());
        transaction7.execute();
        System.out.printf("loan from %s amount of %f -> Failed.\n", businessAccount.getOwnerName(), amount3);
        System.out.printf("Balance of %s is %f.\n", businessAccount.getOwnerName(), businessAccount.getBalance());
        System.out.println("---------------------------------------------------------------");

        /* From basicAccount -> premiumAccount amount of: 20_000 -> rollback */
        double amount4 = 20_000;
        Transaction transaction8 = new Transaction(transactionId++, Transaction.WITHDRAW, amount4, LocalDateTime.now(), basicAccount);
        try {
            System.out.printf("Balance of %s is %f.\n", premiumAccount.getOwnerName(), premiumAccount.getBalance());
            System.out.printf("Balance of %s is %f.\n", businessAccount.getOwnerName(), businessAccount.getBalance());
            transaction8.execute();
        } catch (DepositionFailureException | WithdrawalFailureException e) {
            transaction8.rollback();
            System.out.printf("From %s to %s amount of %f -> Failed.\n", basicAccount.getOwnerName(), premiumAccount.getOwnerName(), amount4);
            System.out.printf("Balance of %s is %f.\n", premiumAccount.getOwnerName(), premiumAccount.getBalance());
            System.out.printf("Balance of %s is %f.\n", businessAccount.getOwnerName(), businessAccount.getBalance());
            System.out.println("---------------------------------------------------------------");
        }

    }
}
