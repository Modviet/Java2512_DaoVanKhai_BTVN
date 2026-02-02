package com.example.BTL2.model;

@FunctionalInterface
public interface StudentFilter {
    boolean filter(Student s);
}
