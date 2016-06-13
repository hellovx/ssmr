package com.zh.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

/*	public static Jedis getJedis() {

		try {
			JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
			return pool.getResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("初始化连接池错误");

	}*/
	
	
	
	private static JedisPool pool;

    /**
 * 建立连接池 真实环境，一般把配置参数缺抽取出来。
 * 
 */
    private static void createJedisPool() {

        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();

        // 设置最大连接数
        //config.setMaxActive(100);

        // 设置最大阻塞时间，记住是毫秒数milliseconds
        //config.setMaxWait(1000);

        // 设置空间连接
        config.setMaxIdle(10);

        // 创建连接池
        pool = new JedisPool(config, "127.0.0.1", 6379);

    }

    /**
 * 在多线程环境同步初始化
 */
    private static synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    /**
 * 获取一个jedis 对象
 * 
 * @return
 */
    public static Jedis getJedis() {

        if (pool == null)
            poolInit();
        Jedis newjedis= pool.getResource();
        newjedis.auth("root");
        return newjedis;
    }

    /**
 * 归还一个连接
 * 
 * @param jedis
 */
    public static void returnRes(Jedis jedis) {
        pool.returnResource(jedis);
    }

    

}
