package com.cjahn.webcrawler.config;

import java.util.concurrent.Executor;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync(proxyTargetClass=true)
public class AsyncConfig {
    @Bean(name = "WebCrawlingExecutor")
    public Executor WebCrawlingExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(30);
        taskExecutor.setThreadNamePrefix("WebCrawlingExecutor-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
