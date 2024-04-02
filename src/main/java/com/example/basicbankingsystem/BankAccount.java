package com.example.basicbankingsystem;

public interface BankAccount {
    String getAccountNumber();
    String getOwnerName();
    double getBalance();
    void deposit(double amount) throws BankException;
    void withdraw(double amount) throws BankException;
    void transfer(BankAccount recipient, double amount) throws BankException;
}