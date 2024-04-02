package com.example.basicbankingsystem;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankService {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    public void createAccount(String accountNumber, String ownerName, double initialBalance) {
        BankAccount account = new SimpleBankAccount(accountNumber, ownerName, initialBalance);
        accounts.put(accountNumber, account);
    }

    public BankAccount getAccount(String accountNumber) throws BankException {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new BankException("Account not found");
        }
        return account;
    }

    public void deposit(String accountNumber, double amount) throws BankException {
        BankAccount account = getAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) throws BankException {
        BankAccount account = getAccount(accountNumber);
        account.withdraw(amount);
    }

    public void transfer(String senderAccountNumber, String recipientAccountNumber, double amount) throws BankException {
        BankAccount senderAccount = getAccount(senderAccountNumber);
        BankAccount recipientAccount = getAccount(recipientAccountNumber);
        senderAccount.transfer(recipientAccount, amount);
    }
}