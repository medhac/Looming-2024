package com.example.loomdemo.virtualthread.scheduler;

import com.example.loomdemo.util.ThreadUtil;

import java.util.stream.IntStream;

public class CooperativeScheduling {

    public static void main(String[] args) {

        var threadList = IntStream.range(0, 10)
                .mapToObj(i -> Thread.ofVirtual()
                        .unstarted(CooperativeScheduling::printCurrentThread)).toList();

        threadList.forEach(Thread::start);
        ThreadUtil.joinAll(threadList);
    }

    static void printCurrentThread(){
        System.out.println(Thread.currentThread());
        ThreadUtil.sleepOfMillis(500);
    }
}
