package com.example.loomdemo.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class LogUtil {

    public String filePath = "C:\\WorkSpace\\Code\\Looming-2024\\loom-demo\\src\\comparison_result";

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
            String fileName = "LOG_"+threadType+".txt";
            createFileInFolder(fileName, filePath, content);
            System.out.println("Time log has been written to the file."+ content);
    }

    public double convertMillisToSeconds(long millis) {
        return millis / 1000.0;
    }

    public void createFileInFolder(String fileName, String folderPath, String content) {
        Path path = Paths.get(folderPath, fileName);
        try {
            if (!Files.exists(path)) {
                System.out.println("File has been created in the specified folder.");
            } else {
                System.out.println("File already exists, appending content.");
            }
            Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
