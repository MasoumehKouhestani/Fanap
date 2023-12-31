package ir.fanap.bankingsystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public abstract class BankAccount {

    protected long accountNumber;
    protected String ownerName;
    protected double balance;

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

}
