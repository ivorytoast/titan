package backend.nyc.com.titan.redis;

import redis.clients.jedis.Jedis;

public class RedisClient {

    private final Jedis jedis;

    public RedisClient() {
        jedis = new Jedis("redis");
    }



}
