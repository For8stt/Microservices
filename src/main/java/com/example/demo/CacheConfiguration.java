package com.example.demo;

import jakarta.persistence.Cacheable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

@Cacheable
@Configuration
@EnableScheduling
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager(){
        ConcurrentMapCacheManager manager= new ConcurrentMapCacheManager();
        manager.setAllowNullValues(false);
        manager.setCacheNames(Arrays.asList("productCache"));
        return manager;
    }
    @CacheEvict(value = "productCache",allEntries = true)
    @Scheduled(fixedDelay = 70000, initialDelay = 0)
    public void evictProductCache(){
        System.out.println("Evicting Product Cache");
    }
}
