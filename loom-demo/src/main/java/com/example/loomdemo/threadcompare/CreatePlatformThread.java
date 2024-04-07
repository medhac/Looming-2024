package com.example.loomdemo.threadcompare;

import com.example.loomdemo.util.LogUtil;
import com.example.loomdemo.util.ThreadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@Component
public class CreatePlatformThread {
    Map<Integer,Double> platformThreadTimeMap = new TreeMap<>();
    @Autowired
    private LogUtil utils;
    public  Map<Integer,Double> createPlatformThread(int numberOfThreads) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfThreads; i++) {
            PlatformThread thread = new PlatformThread();
            thread.start();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        platformThreadTimeMap.put(numberOfThreads,utils.convertMillisToSeconds(totalTime));
        utils.logTime(startTime, endTime, totalTime, ThreadType.PLATFORM.name(), numberOfThreads);
        return platformThreadTimeMap;
    }
}

