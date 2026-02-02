package com.example.BTL2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Student extends Person{
    protected double gpa;
    protected List<Course> enrolledCoures = new ArrayList<>();

    public Student(String id, String name, String email, double gpa) {
        super(id, name, email);
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public List<Course> getEnrolledCoures() {
        return enrolledCoures;
    }

    public void enrollCourse(Course c){
        if(!enrolledCoures.contains(c)){
            enrolledCoures.add(c);
        }
    }

    public double calculateAverageScore(Map<Course,Double> scores){
        if(scores == null || scores.isEmpty()) return 0;
        return scores.values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }

    public abstract double calculateTuitionFee();
}
