package com.example.bai9.bai1;

public abstract class Employee implements IWorkable{
    private String id;
    private String name;
    protected double baseSalary;

    public Employee() {
    }

    public Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract double calculateSalary();

    public void displayInfo(){
        System.out.println("ID : "+id);
        System.out.println("Name : "+name);
        System.out.println("Base Salary : "+String.format("%,.0f",baseSalary)+"VND");
        System.out.println("Total Salary : "+String.format("%,.0f",calculateSalary()) );
    }
}
