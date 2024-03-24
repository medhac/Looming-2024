package com.example.loomdemo.VirtualThread;

import com.example.loomdemo.util.GenerateChart;
import com.example.loomdemo.util.ThreadType;
import com.example.loomdemo.util.ThreadUtil;

import javax.swing.*;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Boundary
{
    public static void main(String[] args) {
        Map<Integer, Double> platformThreadTimeMap = executeTasksOnGivenExecutor(Executors.newCachedThreadPool(), ThreadType.PLATFORM);
        Map<Integer, Double> virtualThreadTimeMap = executeTasksOnGivenExecutor(Executors.newVirtualThreadPerTaskExecutor(), ThreadType.VIRTUAL);

        SwingUtilities.invokeLater(() -> {
            GenerateChart chart = new GenerateChart();
            chart.createCPUBoundComparisonReport(virtualThreadTimeMap,platformThreadTimeMap);
            chart.setSize(800, 800);
            chart.setLocationRelativeTo(null);
            chart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            chart.setTitle("Platform vs Virtual Threads CPU Bound Comparison");
            chart.setVisible(true);
        });
    }

    private static Map<Integer, Double> executeTasksOnGivenExecutor(final ExecutorService executor,  ThreadType threadType) {
        System.out.println("Executing tasks on " + threadType.name() + " threads");
        Map<Integer, Double> timeMap = new TreeMap<>();

        IntStream.rangeClosed(1, 64).forEach(index -> {
            Instant start = Instant.now();
            executor.submit(() -> {
                IntStream.range(0, 50_000_000)
                        .mapToObj(BigInteger::valueOf)
                        .reduce(BigInteger.ZERO, BigInteger::add);

                double time = ThreadUtil.benchmark(start);
                System.out.println(createTwoDigitId(index) + ";" + time);
                timeMap.put(index, time);
            });
        });

        ThreadUtil.shutdownAndAwaitTermination(executor, TimeUnit.HOURS);
        return timeMap;
    }

    private static String createTwoDigitId(final int index){
        var id = String.valueOf(index);
        return id.length() == 1 ? "0" + id : id;
    }
}
