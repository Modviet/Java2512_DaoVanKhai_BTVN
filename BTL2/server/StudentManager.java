package com.example.BTL2.server;

import com.example.BTL2.exception.CourseNotFoundException;
import com.example.BTL2.exception.InvalidScoreException;
import com.example.BTL2.exception.StudentNotFoundException;
import com.example.BTL2.exception.DulicateStudentException;
import com.example.BTL2.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class StudentManager {

    private Repository<Student> studentRepo = new Repository<>();
    private Repository<Course> courseRepo = new Repository<>();

    private Map<String, Map<String,Double>> scoreBoard = new HashMap<>();

    public void initDataClass(){
        studentRepo = new Repository<>();
        courseRepo = new Repository<>();
        scoreBoard.clear();

        for (int i = 1; i <= 5; i++) {
            studentRepo.add("A" + i,
                    new FullTimeStudent("F" + i, "Tran Van Hai " + i, "f" + i + "@mail.com", 7 + i % 3));
            studentRepo.add("B" + i,
                    new PartTimeStudent("P" + i, "Nguyen Thi Ly " + i, "p" + i + "@mail.com", 6 + i % 3));
        }

        courseRepo.add("C1", new Course("C1", "Java", 3));
        courseRepo.add("C2", new Course("C2", "OOP", 3));
        courseRepo.add("C3", new Course("C3", "Database", 2));
        courseRepo.add("C4", new Course("C4", "Web", 3));
        courseRepo.add("C5", new Course("C5", "AI", 4));

        Random rd = new Random();
        for (Student s : studentRepo.findAll()) {
            courseRepo.findAll().stream()
                    .limit(2 + rd.nextInt(2))
                    .forEach(s::enrollCourse);
        }

        System.out.println("✅ Đã khởi tạo dữ liệu mẫu");
    }


    public void addStudent(Student s){
        if(studentRepo.exists(s.getId()))
            throw new DuplicateStudentException("Ma ID sinh vien bi trung");
           studentRepo.add(s.getId(),s);
    }

    public void addCourse(Course c){
        if(courseRepo.exists(c.getCourseId()))
            throw new RuntimeException("Trung ma ID khoa hoc");
             courseRepo.add(c.getCourseId(),c);
    }

    public void enrollStudentToCourse(String sid,String cid){
        Student s = studentRepo.findById(sid)
                .orElseThrow(()-> new StudentNotFoundException("Khong tim thay sinh vien"));

        Course c = courseRepo.findById(cid)
                .orElseThrow(()-> new CourseNotFoundException("Khong tim thay khoa hoc"));

        s.enrollCourse(c);
    }

    public void inputScore(String sid,String cid,double score){
        if(score < 0|| score> 10)
            throw new InvalidScoreException("Diem kiem tra phai tu 1-10");

        studentRepo.findById(sid)
                .orElseThrow(()-> new StudentNotFoundException("Khong tim thay sinh vien"));
        courseRepo.findById(cid)
                .orElseThrow(()-> new CourseNotFoundException("Khong tim thay khoa hoc"));

        scoreBoard.computeIfAbsent(sid,k->new HashMap<>())
                .put(cid,score);
    }

    public synchronized void safeInputScore(String sid,String cid , double score){
        inputScore(sid,cid,score);
    }

    public void printStudentScoreBoard(String sid){
        Student s = studentRepo.findById(sid)
                .orElseThrow(()->new StudentNotFoundException("Khong tim thay sinh vien"));

        Map<String,Double> scores = scoreBoard.get(sid);
        if(scores == null || scores.isEmpty()){
            System.out.println("Sinh vien chua co diem");
            return;
        }

        Map<Course,Double> map = new HashMap<>();
        for (String cid:scores.keySet()){
            Course c= courseRepo.findById(cid).get();
            map.put(c,scores.get(cid));
            System.out.println(c.getCourseName()+" : "+scores.get(cid));
        }

        System.out.println("GPA Trung binh : "+s.calculateAverageScore(map));
    }

    public Optional<Student> searchStudent(String id){
        return studentRepo.findById(id);
    }

    public List<Student> searchStudent(String name,double minGPA){
        return studentRepo.findAll().stream()
                .filter(s->s.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(s->s.getGpa() >= minGPA)
                .collect(Collectors.toList());
    }

    public List<Student> filterStudents(StudentFilter filter) {
        return studentRepo.findAll().stream()
                .filter(filter::filter)
                .collect(Collectors.toList());
    }

    public List<Student> sortStudentsByGpaDesc() {
        return studentRepo.findAll().stream()
                .sorted((a, b) -> Double.compare(b.getGpa(), a.getGpa()))
                .collect(Collectors.toList());
    }

    public List<Student> sortStudentsByNameAsc() {
        return studentRepo.findAll().stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }


    public double calculateTuition(Student s) {
        return s.calculateTuitionFee();
    }

    public List<String> getAllStudentIds() {
        return studentRepo.findAll().stream()
                .map(Student::getId)
                .collect(Collectors.toList());
    }

    public List<String> getAllCourseIds() {
        return courseRepo.findAll().stream()
                .map(Course::getCourseId)
                .collect(Collectors.toList());
    }

}

