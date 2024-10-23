package com.example.repository;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class UserCacheRepository {
    // user id 102
    // usr::102 = {value....}
    public static final String USER_CACHE_PREFIX = "usr::";
    public static final Integer USER_CACHE_KEY_EXPIRY = 600;

    @Autowired
    RedisTemplate<String, User> redisTemplate;

    public void set(User user) {
        redisTemplate.opsForValue().set(getKey(user.getId()), user, USER_CACHE_KEY_EXPIRY, TimeUnit.SECONDS);
    }
    public User get(Integer userId) {
        User user = redisTemplate.opsForValue().get(getKey(userId));
        if(user == null) return null;
        return user;
    }

    public String getKey(Integer userId) {
        return USER_CACHE_PREFIX + userId;
    }
}
