package com.e2open.common.cache;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisServiceManager {
    private static RedisServiceManager redisServiceManager = null;
    private static Logger logger = Logger.getLogger(RedisServiceManager.class.getCanonicalName());

    private JedisPool redisPool;

    public static final int DEFAULT_TIMEOUT = 5000;

    public static synchronized RedisServiceManager getMetadataInstance()
    {
        if (redisServiceManager == null)
        {
            String redis_ip = "127.0.0.1";
            int redis_port = 6379;
            redisServiceManager = new RedisServiceManager(redis_ip,
                    redis_port, 0);
        }
        return redisServiceManager;
    }

    public RedisServiceManager(String redisHost, int redisPort, int dbnum) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //poolConfig.setMaxActive(300);
        poolConfig.setMaxIdle(100);
        poolConfig.setMinIdle(30);
        //poolConfig.setMaxWait(3000);
        redisPool = new JedisPool(poolConfig, redisHost, redisPort, DEFAULT_TIMEOUT, null, dbnum);
    }

//    public RedisServiceManager(String redisHost, int redisPort, int dbnum,int maxActive,int maxIdle,int minIdle) {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        //poolConfig.setMaxActive(maxActive);
//        poolConfig.setMaxIdle(maxIdle);
//        poolConfig.setMinIdle(minIdle);
//        //poolConfig.setMaxWait(3000);
//        redisPool = new JedisPool(poolConfig, redisHost, redisPort, DEFAULT_TIMEOUT, null, dbnum);
//    }

    public Set<String> hkeys(String field) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.hkeys(field);
        } catch (Exception e) {
            return null;
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }


    public List<String> hvals(String field) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.hvals(field);
        } catch (Exception e) {
            return null;
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public List<String> hmget(String key, String[] fields) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            List<String> values = redis.hmget(key, fields);
            return values;
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public String hget(String key, String field) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            String val = redis.hget(key, field);
            // if(field.endsWith("/s"))
            // System.out.println(System.currentTimeMillis()+" - Using REDISS connection : "+redis+" to get "+val+" for "+field+"."+Thread.currentThread().toString());
            return val;
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public Map<String, String> hgetAll(String key) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.hgetAll(key);
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public void hrem(String key, String field) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            redis.hdel(key, field);
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public Long hset(String key, String field, String value) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.hset(key, field, value);
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public String hmset(String key, Map<String,String> fieldValueMap) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.hmset(key, fieldValueMap);
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public long hincrby(String key, String field,long value) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.hincrBy(key, field, value);
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public Long hdel(String key, String field) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.hdel(key, field);
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public Long hsetnx(String key, String field, String value) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.hsetnx(key, field, value);
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    /**
     * Returns number of entries in a given hashmap
     *
     * @param mapName
     * @return
     */
    public Long hlen(String mapName) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.hlen(mapName);
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public void hclear(String key) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            Set<String> fields = redis.hkeys(key);
            for (Iterator<String> fieldsItr = fields.iterator(); fieldsItr.hasNext(); ) {
                String field = fieldsItr.next();
                redis.hdel(key, field);
            }

        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public Long lpush(String key, String value) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.lpush(key, value);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
        return null;
    }

    public List<String> lrange(String key, long start, long end) {
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.lrange(key, start, end);
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public void updateKeyExpiration(String key,final int expiration ){
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            redis.expire(key,expiration);
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }


    public Set<String> smembers(String field) {
        // TODO Auto-generated method stub
        Jedis redis = null;
        try {
            redis = redisPool.getResource();
            return redis.smembers(field);
        } catch (Exception e) {
            return null;
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public void sdd(String key, String[] values){
        Jedis redis = null;
        try{
            redis = redisPool.getResource();
            redis.sadd(key, values);
        }catch (Exception e){
            logger.error(e);
        }finally {
            if (redis != null)
                redisPool.returnResource(redis);
        }
    }

    public void set(String key,String value){
        Jedis redis = null;
        try{
            redis = redisPool.getResource();
            redis.set(key,value);
        }finally {
            if (redis != null)
                redisPool.returnResource(redis);
        }
    }

    public String get(String key){
        Jedis redis = null;
        try{
            redis = redisPool.getResource();
            return redis.get(key);
        }catch (Exception e){
            logger.error(e);
        } finally{
            if (redis != null)
                redisPool.returnResource(redis);
        }
        return null;
    }

    public Long del(String key){
        Jedis redis = null;
        try{
            redis = redisPool.getResource();
            return redis.del(key);
        }catch (Exception e){
            logger.error(e);
        }finally {
            {
                if (redis != null)
                    redisPool.returnResource(redis);
            }
        }
        return null;
    }

    public String flushAll(){
        Jedis redis = null;
        try{
            redis = redisPool.getResource();
            return redis.flushAll();
        }catch (Exception e){
            logger.error(e);
        }finally {
            if (redis != null)
                redisPool.returnResource(redis);
        }
        return null;
    }
}