package com.uday.springcrudopr.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        CaffeineCache instruments=new CaffeineCache("employee", employeeCacheBuilder().build());
        simpleCacheManager.setCaches(Arrays.asList(instruments));
        simpleCacheManager.initializeCaches();
        return simpleCacheManager;
    }
    Caffeine<Object, Object> employeeCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(4)
                .maximumSize(5)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .removalListener(new CustomRemovalListener());
    }
   
    class CustomRemovalListener implements RemovalListener<Object, Object> {

        public void onRemoval(Object key, Object value, RemovalCause cause) {
            System.out.format("removal listerner called with key [%s], cause [%s], evicted [%S], value[%s] \n",
                    key, cause.toString(), cause.wasEvicted(),value.toString());
            System.out.println("current Thread"+Thread.currentThread().getName());
        }
    }
}