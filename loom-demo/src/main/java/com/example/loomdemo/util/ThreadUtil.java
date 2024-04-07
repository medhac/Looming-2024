package com.example.loomdemo.util;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadUtil
{
    private ThreadUtil(){}

    public static void sleepOfSeconds(long duration){
        try {
            Thread.sleep(Duration.ofSeconds(duration));
        } catch (InterruptedException ie){
            Thread.currentThread().interrupt();
        }
    }

    public static void sleepOfMillis(long duration){
        try {
            Thread.sleep(Duration.ofMillis(duration));
        } catch (InterruptedException ie){
            Thread.currentThread().interrupt();
        }
    }

    public static void join(final Thread thread){

        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void joinAll(final List<Thread> threads){
        threads.forEach(ThreadUtil::join);
    }

    public static void wait(final ExecutorService executorService){

        try {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException ie){
            Thread.currentThread().interrupt();
        }
    }

    public static long benchmark(final Instant startTime){
        var endTime = Instant.now();
        return Duration.between(startTime, endTime).toSeconds();
    }

    public static void shutdownAndAwaitTermination(final ExecutorService executor,
                                                   final TimeUnit timeUnit){
        executor.shutdown();

        try {
            executor.awaitTermination(1, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
