package com.gyt.unzip.controller;

import com.gyt.unzip.async.MyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private MyTask myTask;

    @GetMapping
    public void contextLoads() throws InterruptedException {
        myTask.doTaskOne();
        myTask.doTaskTwo();
        myTask.doTaskThree();
    }
}