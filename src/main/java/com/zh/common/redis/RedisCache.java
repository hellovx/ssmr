/**
 *    Copyright 2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.zh.common.redis;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zh.common.MybatisRedisCache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Cache adapter for Redis.
 *
 * @author Eduardo Macarron
 */
public final class RedisCache implements Cache {
	
	private static Logger logger = LoggerFactory
			.getLogger(RedisCache.class);

  private final ReadWriteLock readWriteLock = new DummyReadWriteLock();

  private String id;

  private static JedisPool pool;

  public RedisCache(final String id) {
    if (id == null) {
      throw new IllegalArgumentException("Cache instances require an ID");
    }
    logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>RedisCache:id=" + id);
    this.id = id;
    RedisConfig redisConfig = RedisConfigurationBuilder.getInstance().parseConfiguration();
	pool = new JedisPool(redisConfig, redisConfig.getRedis_host(), redisConfig.getRedis_port(),
			redisConfig.getRedis_connectionTimeout(), redisConfig.getRedis_soTimeout(), redisConfig.getRedis_password(),
			redisConfig.getRedis_database(), redisConfig.getRedis_clientName());
  }

  private Object execute(RedisCallback callback) {
    Jedis jedis = pool.getResource();
    try {
      return callback.doWithRedis(jedis);
    } finally {
      jedis.close();
    }
  }

  public String getId() {
    return this.id;
  }

  public int getSize() {
    return (Integer) execute(new RedisCallback() {
      public Object doWithRedis(Jedis jedis) {
        Map<byte[], byte[]> result = jedis.hgetAll(id.toString().getBytes());
        return result.size();
      }
    });
  }


  public void putObject(final Object key, final Object value) {
    execute(new RedisCallback() {
      public Object doWithRedis(Jedis jedis) {
    	  logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>putObject:" + key + "=" + value);
        jedis.hset(id.toString().getBytes(), key.toString().getBytes(), SerializeUtil.serialize(value));
        return null;
      }
    });
  }


  public Object getObject(final Object key) {
    return execute(new RedisCallback() {
      public Object doWithRedis(Jedis jedis) {
        Object value=SerializeUtil.unserialize(jedis.hget(id.toString().getBytes(), key.toString().getBytes()));
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>getObject:" + key + "=" + value);
        return value;
      }
    });
  }

  public Object removeObject(final Object key) {
    return execute(new RedisCallback() {
      public Object doWithRedis(Jedis jedis) {
    	  logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>removeObject:" + key );
        return jedis.hdel(id.toString(), key.toString());
      }
    });
  }

  public void clear() {
    execute(new RedisCallback() {
      public Object doWithRedis(Jedis jedis) {
        jedis.del(id.toString());
        return null;
      }
    });

  }

  public ReadWriteLock getReadWriteLock() {
    return readWriteLock;
  }

  @Override
  public String toString() {
    return "Redis {" + id + "}";
  }

}
