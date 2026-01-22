package com.example.bai9.bai1;

public class Developer extends Employee{
    private String[] skill;

    public Developer(String id, String name, double baseSalary, String[] skill) {
        super(id, name, baseSalary);
        this.skill = skill;
    }

    @Override
    public void doWork() {
        System.out.println("Developer dang chay code..");
    }

    @Override
    public double calculateSalary() {
        return baseSalary * 1.5;
    }

    public String[] getSkill() {
        return skill;
    }

    public void setSkill(String[] skill) {
        this.skill = skill;
    }
}
