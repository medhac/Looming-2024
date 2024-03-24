package com.example.loomdemo.virtualthread.pool;

import com.example.loomdemo.util.ThreadType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListPlatformAndVirtualThread {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
//
//        executorService.execute(() -> {
//            Thread currentThread = Thread.currentThread();
//            System.out.println("Current Thread: " + currentThread.getName());
//            System.out.println("Is current thread virtual: " + currentThread.isVirtual());
//        });
//
//        executorService.shutdown();

        for (int i = 1; i <= 10; i++) {
            Thread.startVirtualThread(() -> {
                // Code to be executed by each thread
                System.out.println("Current Thread: " + Thread.currentThread().getName());
                System.out.println("Is current thread virtual: " + Thread.currentThread().isVirtual());
            });
        }

    }
}