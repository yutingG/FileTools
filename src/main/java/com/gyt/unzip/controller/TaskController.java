package com.gyt.unzip.controller;

import com.gyt.unzip.async.MyTask;
import com.gyt.unzip.service.TaskTest2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private MyTask myTask;

    @Autowired
    private TaskTest2Service taskTest2Service;

    @GetMapping("/test1")
    public String contextLoads() throws InterruptedException {
        myTask.doTaskOne();
        myTask.doTaskTwo();
        myTask.doTaskThree();
        return "success";
    }

    @GetMapping("/test2")
    public String contextLoads2() throws InterruptedException, ExecutionException {
        taskTest2Service.taskTest2();
        return "请求成功";
    }
}