package wang.redder.kiter.cache.redis;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 连接池工厂
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/14 14:20
 */

public class RedisPoolFactory {

    public static JedisPool JedisPoolFactory(JedisPoolConfig poolConfig, RedisConfig redisConfig) {
        // 如果用户没有指定连接池配置，使用默认的配置
        if(poolConfig == null) {
            poolConfig = new JedisPoolConfig();
            poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
            poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
            poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        }

        JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout() * 1000);
        return jp;
    }

}
