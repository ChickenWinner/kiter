package cache.redis;

import file.Person;
import org.junit.Test;
import redis.clients.jedis.JedisPool;
import wang.redder.kiter.cache.redis.RedisOperator;

/**
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/20 20:16
 */
public class RedisTest {

    // 测试Redis操作
    @Test
    public void testRedisOperator() {
        // 初始化redis操作器
        // 拥有多个构造函数，这里实例最简单的构造函数
        RedisOperator redisOperator = new RedisOperator("127.0.0.1", 6379);

        // 获取redis连接池，适用于想自由发挥的同学
        JedisPool jedisPool = redisOperator.getJedisPool();

        // -----以下是实现一些常用的redis命令-----

        // 根据key得到值，可以转为对应的对象
        redisOperator.get("key", Person.class);
        // 设置key，可以设置过期时间，小于等于0表示永不过期
        redisOperator.set("key", 0, "value"); // 设置结果 true成功 false失败
        // 判断key是否存在
        redisOperator.exists("key");
        // 对应key值加减1 用于数字类型
        redisOperator.incr("key"); // 返回加1后的结果
        redisOperator.decr("key"); // 返回减1后的结果
    }
}
