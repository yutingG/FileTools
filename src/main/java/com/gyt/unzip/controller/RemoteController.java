package com.gyt.unzip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 用于模拟远程调用及异步请求
 */
@RestController
@RequestMapping("/remote")
public class RemoteController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/call")
    public String call() {
        String url = "http://localhost:8077/task/test2";
        restTemplate.getForEntity(url, String.class);
        return "success";
    }

    @PostMapping("/answer")
    public String answer(@RequestParam("answer") String answer) {
        return "hello" + answer;
    }
}