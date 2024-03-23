package com.example.loomdemo;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Service
public class Utils {

    public String getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public void logTime(long startTime, long endTime, long totalTime, String threadType,Integer numberOfThreads) {
        String content = "----------------------------------------\n"+
                "DateTime: " + getDateTime() + "\n" +
                "Type : " + threadType + "\n" +
                "Number of Threads: " + numberOfThreads + "\n" +
                "Start time: " + startTime + "\n" +
                "End time: " + endTime + "\n" +
                "Total execution time: " + convertMillisToSeconds(totalTime) + " seconds\n"+
                "----------------------------------------\n";

        try {
            Files.write(Path.of("log_"+threadType+".txt"), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Time log has been written to the file."+ content);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public double convertMillisToSeconds(long millis) {
        return millis / 1000.0;
    }
}
