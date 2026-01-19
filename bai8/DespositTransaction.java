package com.example.bai8;

public class DespositTransaction extends Transaction{
    public DespositTransaction() {
    }

    public DespositTransaction(String id, double amount) {
        super(id, amount);
    }

    @Override
    public void process() {
        System.out.println("Giao dich gui tien dang hoan tat....");
    }
}
