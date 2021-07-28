package com.rust.demo.config;

import com.rust.demo.util.SpringContextUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MybatisCache implements Cache {

    /**
     * id是必须的带上的，这里的id会指定当前放入缓存的mapper的namespace
     */
    private final String id;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private final RedisTemplate<String, Object> redisTemplate;

    public MybatisCache(String id) {
        this.id = id;
        this.redisTemplate = SpringContextUtil.getBean("redisTemplate1", RedisTemplate.class);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        redisTemplate.opsForHash().put(this.id, key, value);
    }

    @Override
    public Object getObject(Object key) {
        return redisTemplate.opsForHash().get(this.id, key);
    }

    @Override
    public Object removeObject(Object key) {
        return redisTemplate.opsForHash().delete(this.id, key);
    }

    @Override
    public void clear() {
        try {
            Set<String> keys = scan(this.id);
            if (!keys.isEmpty()) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        return Math.toIntExact(redisTemplate.opsForHash().size(this.id));
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    public Set<String> scan(String matchKey) {
        Set<String> keys = redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keysTmp = new HashSet<>();
            Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match("*" + matchKey + "*").count(1000).build());
            while (cursor.hasNext()) {
                keysTmp.add(new String(cursor.next()));
            }
            return keysTmp;
        });
        return keys;
    }

    /**
     * MD5加密存储key,以节约内存
     */
    private String MD5Encrypt(Object key) {
        return DigestUtils.md5DigestAsHex(key.toString().getBytes());
    }

}
