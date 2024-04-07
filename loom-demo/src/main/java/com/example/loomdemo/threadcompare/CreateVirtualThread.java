package com.example.loomdemo.threadcompare;

import com.example.loomdemo.util.ThreadType;
import com.example.loomdemo.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
@Component
public class CreateVirtualThread {

    @Autowired
    private LogUtil utils;
    Map<Integer,Double> virtualthreadTimeMap = new TreeMap<>();
    public   Map<Integer,Double> createVirtualThread(int numberOfThreads) {
        long startTime = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(numberOfThreads); // Initialize the latch with the number of threads

        for (int i = 1; i <= numberOfThreads; i++) {
            int finalI = i;
            Thread.startVirtualThread(() -> {
                // Code to be executed by each thread
                System.out.println(ThreadType.VIRTUAL.name() +" : "+ Thread.currentThread().getName()+"-"+ finalI + " is running");
                latch.countDown(); // Decrement the latch count after the thread finishes
            });
        }

        try {
            latch.await(); // Wait for all threads to finish
        } catch (InterruptedException e) {
            System.out.println("An error occurred while waiting for the threads to finish: " + e.getMessage());
        }

        if (latch.getCount() == 0 ){
            System.out.println(ThreadType.VIRTUAL.getDesc() + " : " + latch.getCount());
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            virtualthreadTimeMap.put(numberOfThreads,utils.convertMillisToSeconds(totalTime));
            utils.logTime(startTime, endTime, totalTime, ThreadType.VIRTUAL.name(),numberOfThreads);
            System.out.println(ThreadType.VIRTUAL.getDesc() + ": end");
        }
        return virtualthreadTimeMap;
    }
}