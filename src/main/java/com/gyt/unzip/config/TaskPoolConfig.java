package com.gyt.unzip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class TaskPoolConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 核心线程数10，线程池创建的初始化线程数
        executor.setMaxPoolSize(20); // 最大线程数20，线程池最大的线程数
        executor.setQueueCapacity(200); // 缓冲队列200
        executor.setKeepAliveSeconds(60); // 允许线程的空闲时间60秒
        executor.setThreadNamePrefix("taskExecutor-"); // 线程池名的前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 线程池对拒绝任务的处理策略
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}