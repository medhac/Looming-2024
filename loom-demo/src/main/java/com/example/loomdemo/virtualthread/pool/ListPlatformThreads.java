package com.example.loomdemo.virtualthread.pool;

import com.example.loomdemo.util.ThreadUtil;

import java.time.Instant;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class ListPlatformThreads
{
    private static final Pattern WORKER_PATTERN = Pattern.compile("worker-+[0-9]");
    private static final Pattern POOL_PATTERN = Pattern.compile("@ForkJoinPool-+[0-9]");

    private static final Pattern VIRTUAL_WORKER_PATTERN = Pattern.compile("");
    private static final Set<String> poolNames = ConcurrentHashMap.newKeySet();
    private static final Set<String> pThreadNames = ConcurrentHashMap.newKeySet();
    private static final Set<String> vThreadNames = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) {

        var threadList = IntStream
                .range(0, 50)
                .mapToObj(i -> Thread.ofVirtual().unstarted(() -> {

                    var poolName = getPoolName();
                    poolNames.add(poolName);

                    var workerName = getWorkerName();
                    pThreadNames.add(workerName);

                    var virtualWorkerName = getVirtualWorkerName();
                    vThreadNames.add(virtualWorkerName);

                })).toList();

        var start = Instant.now();
        threadList.forEach(Thread::start);
        ThreadUtil.joinAll(threadList);

        System.out.println("Execution time:  " + ThreadUtil.benchmark(start) + " s");
        System.out.println("Core             " + Runtime.getRuntime().availableProcessors());
        System.out.println("Pools            " + poolNames.size());
        System.out.println(("Pool Name       " + poolNames));
        System.out.println("Platform threads " + pThreadNames.size());
        System.out.println("P thread Names   " + pThreadNames);
        System.out.println("Virtual threads  " + vThreadNames.size());
        System.out.println("V thread Names   " + vThreadNames);
    }

    private static String getWorkerName(){
        var name = Thread.currentThread().toString();
        Matcher workerMatcher = WORKER_PATTERN.matcher(name);
        if(workerMatcher.find()){
            return workerMatcher.group();
        }

        return "worker name not found";
    }

    private static String getVirtualWorkerName(){
       return Thread.currentThread().isVirtual()? "Virtual-["+Thread.currentThread().threadId()+"]":"";
    }

    private static String getPoolName(){
        var name = Thread.currentThread().toString();
        Matcher poolMatcher = POOL_PATTERN.matcher(name);
        if(poolMatcher.find()){
            return poolMatcher.group();
        }

        return "pool name not found";
    }
}

