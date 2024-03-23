package com.example.loomdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreateThread   {

    @Autowired
    private Utils utils;
    public  void createThreadOldWay (int numberOfThreads) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfThreads; i++) {
            MyThread thread = new MyThread();
            thread.start();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        utils.logTime(startTime, endTime, totalTime, "Old_Thread",numberOfThreads);
    }
}

