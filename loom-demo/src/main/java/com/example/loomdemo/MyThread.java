package com.example.loomdemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class MyThread extends Thread {
    @Override
    public void run() {
        // Code to be executed by each thread
//        Path sourcePath = Paths.get("source.txt");
//        Path destinationPath = Paths.get("destination.txt");
        System.out.println("Thread " + Thread.currentThread().getName() + " is running");
//        try {
//            byte[] bytes = Files.readAllBytes(sourcePath);
//            Files.write(destinationPath, bytes);
//            System.out.println("File has been copied successfully.");
//        } catch (IOException e) {
//            System.out.println("An error occurred while copying the file.");
//            e.printStackTrace();
//        }
    }
}