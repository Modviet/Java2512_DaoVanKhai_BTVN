package com.example.BTL2.server;

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
        for(String sid: studentIds){
        for(String cid : courseIds){
            manager.safeInputScore(sid,cid,rd.nextDouble() * 10);
        }
       }
    }
}
