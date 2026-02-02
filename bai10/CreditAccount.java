package com.example.bai10;

import java.util.Scanner;

public class CreditAccount extends Account{

    public int limitCredit;


    @Override
    public void input() {
        super.input();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap han muc tin dung : ");
        this.limitCredit = sc.nextInt();
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Han muc tin dung : "+this.limitCredit);
    }

    public int getLimitCredit() {
        return limitCredit;
    }

    public void setLimitCredit(int limitCredit) {
        this.limitCredit = limitCredit;
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(super.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        if(super.getBalance()< 0 && Math.abs(super.getBalance() -amount) > limitCredit){
            System.out.println("Vuot qua han muc tin dung !");
            return;
        }
        super.setBalance(super.getBalance() - amount);
    }

    @Override
    public void transfer(Account toAccount, double amount) {
        if(super.getBalance() - amount < -limitCredit){
            System.out.println("Khong the chuyen tien vi vuot qua han muc tin dung");
            return;
        }
        this.withdraw(amount);
        toAccount.deposit(amount);
        System.out.println("Chuyen tien thanh cong.");
    }

    @Override
    public String getAccountType() {
        return "CREDIT";
    }
}
