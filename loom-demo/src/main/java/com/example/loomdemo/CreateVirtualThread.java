package com.example.loomdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
@Component
public class CreateVirtualThread {

    @Autowired
    private Utils utils;

    private static String threatType = "Virtual_Thread";

    public  void readFileWithVirtualThread(int numberOfThreads) {
        long startTime = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(numberOfThreads); // Initialize the latch with the number of threads

        for (int i = 1; i <= numberOfThreads; i++) {
            int finalI = i;
            Thread.startVirtualThread(() -> {
                // Code to be executed by each thread
                System.out.println("Virtual Thread " + Thread.currentThread().getName()+"-"+ finalI + " is running");
                latch.countDown(); // Decrement the latch count after the thread finishes
            });
        }

        try {
            latch.await(); // Wait for all threads to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (latch.getCount() == 0 ){
            System.out.println("Virtual thread count: " + latch.getCount());
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            utils.logTime(startTime, endTime, totalTime, threatType,numberOfThreads);
            System.out.println("Virtual thread end");
        }
    }
}