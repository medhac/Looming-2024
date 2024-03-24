package com.example.loomdemo.threadcompare;

import com.example.loomdemo.util.ThreadType;
import org.springframework.stereotype.Component;

@Component
class PlatformThread extends Thread {
    @Override
    public void run() {
        System.out.println(ThreadType.PLATFORM+" : " + Thread.currentThread().getName() + " is running");
    }
}