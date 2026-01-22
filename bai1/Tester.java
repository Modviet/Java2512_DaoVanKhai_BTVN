package com.example.bai9.bai1;

public class Tester extends Employee{
    private int bugFound;

    public Tester(String id, String name, double baseSalary, int bugFound) {
        super(id, name, baseSalary);
        this.bugFound = bugFound;
    }

    @Override
    public void doWork() {
        System.out.println("So loi duoc tim thay : ");
    }

    @Override
    public double calculateSalary() {
        return baseSalary + bugFound* 100000;
    }
}
