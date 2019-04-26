package wang.redder.kiter.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import wang.redder.kiter.json.JsonUtil;


/**
 * redis操作类
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/14 14:20
 */
public class RedisOperator {

    // redis连接池
    private JedisPool jedisPool = null;
    // 连接池配置
    private JedisPoolConfig jedisPoolConfig = null;
    // redis配置
    private RedisConfig redisConfig = new RedisConfig();

    // 构造方法 只有主机和端口号
    public RedisOperator(String host, int port) {
        redisConfig.setPort(port);
        redisConfig.setHost(host);
        init();
    }

    // 构造方法 常用线程池配置
    public RedisOperator(String host, int port,
                         int poolMaxTotal, int poolMaxIdle, int poolMaxWait) {
        this(host, port);
        redisConfig.setPoolMaxTotal(poolMaxTotal);
        redisConfig.setPoolMaxIdle(poolMaxIdle);
        redisConfig.setPoolMaxWait(poolMaxWait);
        init();
    }

    // 构造方法 可以指定redis连接池配置
    public RedisOperator(String host, int port,
                         JedisPoolConfig jedisPoolConfig) {
        this(host, port);
        this.jedisPoolConfig = jedisPoolConfig;
        init();
    }

    // 构造redis连接池
    private void init() {
        this.jedisPool = RedisPoolFactory.JedisPoolFactory(jedisPoolConfig, redisConfig);
    }

    /**
     * 获取连接池
     *
     * @return redis连接池
     */
    public JedisPool getJedisPool() {
        return this.jedisPool;
    }


    /**
     * 获取一个连接
     *
     * @return 一个redis连接
     */
    public Jedis getJedis() {return this.jedisPool.getResource();}


    /**
     * 根据key获取值
     *
     * @param key   key值
     * @param clazz 获取到的结果类型
     * @param <T>   类型泛型
     * @return 返回结果
     */
    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            T t = JsonUtil.stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 设置key value
     *
     * @param key           key值
     * @param expireSeconds 过期时间 <=0为不过期
     * @param value         value值，如果是对象，会将对象转为String进行存储
     * @param <T>           泛型
     * @return 是否设置成功
     */
    public <T> boolean set(String key, int expireSeconds, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = JsonUtil.beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            if (expireSeconds <= 0) {
                jedis.set(key, str);
            } else {
                jedis.setex(key, expireSeconds, str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * @param key key值
     * @return 指定的key是否存在
     */
    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * @param key 指定key加1
     * @return 加1后的结果
     */
    public Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * @param key 指定key减1
     * @return 减1后的结果
     */
    public Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } finally {
            returnToPool(jedis);
        }
    }



    

    /**
     * 关闭连接
     *
     * @param jedis 连接
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
