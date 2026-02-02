package com.example.bai10;

import java.util.Scanner;

public abstract class Account implements IBankingAction {
    private String numberAccount;
    private String ownerName;
    private double balance;

    public void input(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so tai khoan : ");
        this.numberAccount = sc.nextLine();
        System.out.println("Nhap ten nguoi dung : ");
        this.ownerName = sc.nextLine();
        System.out.println("Nhap so du : ");
        this.balance = sc.nextDouble();
    }

    public void display(){
        System.out.println("So tai khoan : "+this.numberAccount);
        System.out.println("Ten nguoi dung : "+this.ownerName);
        System.out.println("So du : "+this.balance);
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract String getAccountType();
}
