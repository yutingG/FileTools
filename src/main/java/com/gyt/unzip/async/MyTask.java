package com.gyt.unzip.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 *
 */
@Component
public class MyTask {

    public static Random random = new Random();

    @Async
    public void doTaskOne() throws InterruptedException {
        System.out.println("开始任务1");
        long startTime = System.currentTimeMillis();
        Thread.sleep(random.nextInt(5000));
        long endTime = System.currentTimeMillis();
        System.out.println("完成任务1，耗时：" + (endTime - startTime) + "ms");
    }

    @Async
    public void doTaskTwo() throws InterruptedException {
        System.out.println("开始任务2");
        long startTime = System.currentTimeMillis();
        Thread.sleep(random.nextInt(5000));
        long endTime = System.currentTimeMillis();
        System.out.println("完成任务2，耗时：" + (endTime - startTime) + "ms");
    }

    @Async
    public void doTaskThree() throws InterruptedException {
        System.out.println("开始任务3");
        long startTime = System.currentTimeMillis();
        Thread.sleep(random.nextInt(5000));
        long endTime = System.currentTimeMillis();
        System.out.println("完成任务3，耗时：" + (endTime - startTime) + "ms");
    }
}
