package com.gyt.unzip.service;

import com.gyt.unzip.async.MyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class TaskTest2Service {

    @Autowired
    private MyTask myTask;

    @Async
    public void taskTest2() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        Future<String> task1 = myTask.doTaskOne();
        Future<String> task2 = myTask.doTaskTwo();
        Future<String> task3 = myTask.doTaskThree();
        System.out.println(task1.get());
        System.out.println(task2.get());
        System.out.println(task3.get());
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "ms");
    }
}