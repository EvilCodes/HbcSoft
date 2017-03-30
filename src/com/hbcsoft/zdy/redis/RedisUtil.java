package com.hbcsoft.zdy.redis;

import java.util.ArrayList;
import java.util.List;

import com.yaja.common.constant.YAJAConstant;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class RedisUtil {
	private transient final Jedis jedis;
	private transient JedisPool jedisPool;
	private transient ShardedJedisPool shardedJedisPool;

	public RedisUtil() {
		initialPool();
		initialShardedPool();
		shardedJedisPool.getResource();
		jedis = jedisPool.getResource();
	}

	private void initialPool() {
		final JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);

		jedisPool = new JedisPool(config, YAJAConstant.REDIS_ADDRESS, YAJAConstant.REDIS_PORT);
	}

	private void initialShardedPool() {
		final JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);
		final List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(
				new JedisShardInfo(YAJAConstant.REDIS_ADDRESS, YAJAConstant.REDIS_PORT, YAJAConstant.SHARD_NAME_SQL));

		shards.add(
				new JedisShardInfo(YAJAConstant.REDIS_ADDRESS, YAJAConstant.REDIS_PORT, YAJAConstant.SHARD_NAME_SYS));

		shards.add(
				new JedisShardInfo(YAJAConstant.REDIS_ADDRESS, YAJAConstant.REDIS_PORT, YAJAConstant.SHARD_NAME_CACHE));

		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	public String getValue(final String key)
	{
		final String value = jedis.get(key);
		jedisPool.returnResource(jedis);
		return value;
	}
	
	public void setValue(final String key, String value)
	{
		value = jedis.set(key, value);
		jedisPool.returnResource(jedis);
	}
}
