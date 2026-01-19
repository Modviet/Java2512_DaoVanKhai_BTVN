package com.example.bai8;

public class WithdrawTransaction extends Transaction{
    public WithdrawTransaction(String id, double amount) {
        super(id, amount);
    }

    @Override
    public void process() {
        System.out.println("Giao dich rut tien dang hoan tat...");
    }
}
