package com.example.loomdemo.VirtualThread;

import com.example.loomdemo.util.ThreadType;
import com.example.loomdemo.util.ThreadUtil;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Benchmark
{

    public static void main(String[] args) {

        var platformThreadExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        var virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();

        executeTasksOnGivenExecutor(platformThreadExecutor, ThreadType.PLATFORM);
        executeTasksOnGivenExecutor(virtualThreadExecutor, ThreadType.VIRTUAL);
    }

    private static void executeTasksOnGivenExecutor(final ExecutorService executor, final ThreadType type){

        var startTime = Instant.now();
        IntStream.range(0, 1000)
                .mapToObj(i -> {
                    Runnable runnable = () -> ThreadUtil.sleepOfMillis(50);
                    return runnable;
                }).map(executor::submit).toList();

        ThreadUtil.wait(executor);
        System.out.printf("Completion time of %s %s ms%n", type.getDesc(), ThreadUtil.benchmark(startTime));
    }
}
