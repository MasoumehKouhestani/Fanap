package ir.fanap.bankingsystem;

import ir.fanap.bankingsystem.accounttype.PremiumAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class Transaction {

    public static final String LOAN = "loan";
    public static final String INVEST = "invest";
    public static final String DEPO = "depo";
    public static final String WITHDRAW = "withdraw";
    private long transactionId;
    private String transactionType;
    private double amount;
    private LocalDateTime transactionDate;
    private BankAccount account;

    /**
     * Instructions:
     * - depo <accountNumber> <amount>
     * - withdraw <accountNumber> <amount>
     * - loan <accountNumber> <amount>
     * - invest <accountNumber> <amount>
     */
    public void execute() {
        switch (this.transactionType) {
            case LOAN -> {
                if (this.account instanceof PremiumAccount) {
                    ((PremiumAccount) this.account).loan(this.amount);
                } else {
                    System.out.println("Not Supported for your account type!");
                }
            }
            case INVEST -> {
                if (this.account instanceof PremiumAccount) {
                    ((PremiumAccount) this.account).invest(this.amount);
                } else {
                    System.out.println("Not Supported for your account type!");
                }
            }
            case DEPO -> account.deposit(this.amount);
            case WITHDRAW -> account.withdraw(this.amount);
        }
    }

    public void rollback() {
        switch (this.transactionType) {
            case DEPO -> this.account.withdraw(this.amount);
            case WITHDRAW -> this.account.deposit(this.amount);
        }
    }
}
