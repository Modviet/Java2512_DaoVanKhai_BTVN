package com.example.BTL2.model;

import javax.print.DocFlavor;

public class PartTimeStudent extends Student{
    private static final double PRICE_PER_COURSE = 500;

    public PartTimeStudent(String id, String name, String email, double gpa) {
        super(id, name, email, gpa);
    }

    @Override
    public String getRole() {
        return "PART_TIME";
    }

    @Override
    public double calculateTuitionFee() {
        return enrolledCoures.size() * PRICE_PER_COURSE;
    }
}
