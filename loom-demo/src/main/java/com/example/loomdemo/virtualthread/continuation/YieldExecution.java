package com.example.loomdemo.virtualthread.continuation;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

public class YieldExecution
{
    private static final String SCOPE_NAME = "yazilimci-gunleri";

    public static void main(String[] args) {

        ContinuationScope scope = new ContinuationScope(SCOPE_NAME);

        Continuation continuation = new Continuation(scope, () -> {
            System.out.println("Continuation is running");
            Continuation.yield(scope);
            System.out.println("Continuation is still running");
        });

        continuation.run();
        //continuation.run();
    }


}
