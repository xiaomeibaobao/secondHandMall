package com.hzs.shop.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 220419
 * @description
 * @date 2026/9/17
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }
    public void zAdd(String key, Object value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }
    public Double zIncrementScore(String key, Object value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }
    public Set<Object> zReverseRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }
    public Long zRemove(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }
    public Double zScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }
    public Long zSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

}