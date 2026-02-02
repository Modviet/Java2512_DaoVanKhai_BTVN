package com.example.BTL2.model;

public class FullTimeStudent extends Student{
    private static final double PRICE_PER_CREDIT = 300;

    public FullTimeStudent(String id, String name, String email, double gpa) {
        super(id, name, email, gpa);
    }

    @Override
    public String getRole() {
        return "FULL_TIME";
    }

    @Override
    public double calculateTuitionFee() {
        return enrolledCoures.stream()
                .mapToInt(Course::getCredits)
                .sum() * PRICE_PER_CREDIT;
    }
}
