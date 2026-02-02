package com.example.bai10;

public interface IBankingAction {
    void deposit(double amount);
    void withdraw(double amount);
    void transfer(Account toAccount,double amount);
}
