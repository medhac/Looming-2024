package com.example.loomdemo.virtualthread.monitor;
/*
* There are two cases where a blocking operation doesn't unmount the virtual
*  thread from the carrier thread:
*   1. When the virtual thread executes a synchronized block or method code
*  2. When it calls a native method or a foreign function
*  In these cases, the virtual thread is pinned to the carrier thread.
*  This is called pinning event.
* */
import com.example.loomdemo.util.ThreadUtil;

public class MonitoringPinningEvent {
    private static final Object lock = new Object();

    public static void main(String[] args) {

        var thread = Thread.ofVirtual().unstarted(() -> {
            //To see the pinning event, execute this class with runMonitoringPinningEvent.sh file
            System.out.println("This virtual thread will be pinned on its carrier thread");
            synchronized (lock){
                ThreadUtil.sleepOfMillis(1);
            }
        });

        thread.start();
        ThreadUtil.join(thread);
    }
}
