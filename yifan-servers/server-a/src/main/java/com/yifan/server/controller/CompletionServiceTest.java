package com.yifan.server.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletionServiceTest {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(5);
        CompletionService<String> completionService = new ExecutorCompletionService<>(service);
        for (int i = 5; i > 0; i--) {
            completionService.submit(new ReturnAfterSleepCallable(i));
        }
        System.out.println("after submit");
        for (int i = 5; i > 0; i--) {
            System.out.println("result: " + completionService.take().get()); // 这个方法是阻塞的
        }
        System.out.println("after get");
        service.shutdown();
    }

    private static class ReturnAfterSleepCallable implements Callable<String> {
        int sleep;

        public ReturnAfterSleepCallable(int sleep) {
            this.sleep = sleep;
        }

        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(sleep);
            return System.currentTimeMillis() + ",sleep=" + String.valueOf(sleep);
        }
    }
}
