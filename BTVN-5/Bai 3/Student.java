package com.example;

public class Student {
    private String id;
    private String name;
    private double attendanceScore;
    private double examScore;

    public Student(String id, String name, double attendanceScore, double examScore) {
        this.id = id;
        this.name = name;
        this.attendanceScore = attendanceScore;
        this.examScore = examScore;
    }

    public Student() {
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

    public double getAttendanceScore() {
        return attendanceScore;
    }

    public void setAttendanceScore(double attendanceScore) {
        this.attendanceScore = attendanceScore;
    }

    public double getExamScore() {
        return examScore;
    }

    public void setExamScore(double examScore) {
        this.examScore = examScore;
    }

    public double calculateFinalScore(){
        return attendanceScore * 0.3 + examScore * 0.7;
    }

    public String getGrade(){
        double score = calculateFinalScore();
        if(score >= 8.5){
            return "A";
        } else if(score >= 7.0){
            return "B";
        } else if(score >= 5.5){
            return "C";
        } else {
            return "D";
        }
    }
}
