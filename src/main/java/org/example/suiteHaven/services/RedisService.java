package org.example.suiteHaven.services;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String, Long> redisTemplate;

    public RedisService(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveValue(String key, Long value){
        redisTemplate.opsForValue().set(key, value, 24, TimeUnit.HOURS);
    }

    public Long getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
