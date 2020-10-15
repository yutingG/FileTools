package com.gyt.unzip.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 *
 */
@Component
public class MyTask {

    public static Random random = new Random();

    @Async("taskExecutor")
    public Future<String> doTaskOne() throws InterruptedException {
        System.out.println("开始任务1");
        long startTime = System.currentTimeMillis();
        Thread.sleep(random.nextInt(5000));
        long endTime = System.currentTimeMillis();
        System.out.println("完成任务1，耗时：" + (endTime - startTime) + "ms");
        return new AsyncResult<>("任务1完成");
    }

    @Async("taskExecutor")
    public Future<String> doTaskTwo() throws InterruptedException {
        System.out.println("开始任务2");
        long startTime = System.currentTimeMillis();
        Thread.sleep(random.nextInt(5000));
        long endTime = System.currentTimeMillis();
        System.out.println("完成任务2，耗时：" + (endTime - startTime) + "ms");
        return new AsyncResult<>("任务2完成");
    }

    @Async("taskExecutor")
    public Future<String> doTaskThree() throws InterruptedException {
        System.out.println("开始任务3");
        long startTime = System.currentTimeMillis();
        Thread.sleep(random.nextInt(5000));
        long endTime = System.currentTimeMillis();
        System.out.println("完成任务3，耗时：" + (endTime - startTime) + "ms");
        return new AsyncResult<>("任务3完成");
    }
}
