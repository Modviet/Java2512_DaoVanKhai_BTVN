package com.example.BTL2.server;

import com.example.BTL2.model.Course;
import com.example.BTL2.model.Student;

import java.util.List;
import java.util.Random;

public class ScoreInputTask implements Runnable{
    private List<String> studentIds;
    private List<String> courseIds;
    private StudentManager manager;

    public ScoreInputTask(List<String> studentIds, List<String> courseIds, StudentManager manager) {
        this.studentIds = studentIds;
        this.courseIds = courseIds;
        this.manager = manager;
    }

    @Override
    public void run() {
        Random rd = new Random();
            for(String sid : studentIds){
                Student s = manager.searchStudent(sid).orElse(null);
                if(s == null) continue;

                for(Course c : s.getEnrolledCoures()){
                    double score = rd.nextDouble() * 10;
                    manager.safeInputScore(sid,c.getCourseId(),score);
                }

       }
    }


}
