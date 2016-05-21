package com.wd.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisCache implements Cache {
	private static Log log = LogFactory.getLog(RedisCache.class);
	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String id;

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("必须传入ID");
		}
		log.debug("MybatisRedisCache:id=" + id);
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public int getSize() {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		int result = 0;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			result = Integer.valueOf(jedis.dbSize().toString());
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		return result;

	}

	@Override
	//添加缓存数据
	public void putObject(Object key, Object value) {
		if (log.isDebugEnabled())
			log.debug("putObject:" + key.hashCode() + "=" + value);
		if (log.isInfoEnabled())
			log.info("put to redis sql :" + key.toString());
		Jedis jedis = null;
		// 创建新池
		JedisPool jedisPool = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			jedis.set(SerializeUtil.serialize(key.hashCode()), SerializeUtil.serialize(value));
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}

	}

	@Override
	//获得缓存数据
	public Object getObject(Object key) {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		Object value = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			value = SerializeUtil.unserialize(jedis.get(SerializeUtil.serialize(key.hashCode())));
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		if (log.isDebugEnabled())
			log.debug("getObject:" + key.hashCode() + "=" + value);
		return value;
	}

	@Override
	public Object removeObject(Object key) {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		Object value = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			value = jedis.expire(SerializeUtil.serialize(key.hashCode()), 0);
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		if (log.isDebugEnabled())
			log.debug("getObject:" + key.hashCode() + "=" + value);
		return value;
	}

	@Override
	public void clear() {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			jedis.flushDB();
			jedis.flushAll();
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	/**
	 * 
	 * @ClassName: CachePool
	 * @Description: TODO(单例Cache池)
	 * @author LiuYi
	 * @date 2014年6月17日 上午10:50:52
	 * 
	 */
	public static class CachePool {
		JedisPool pool;
		private static final CachePool cachePool = new CachePool();

		public static CachePool getInstance() {
			return cachePool;
		}

		private CachePool() {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(100);
			config.setMaxWaitMillis(1000l);
			// PropertiesLoader pl = new
			// PropertiesLoader("classpath:redis.properties");
			// try {
			// System.out.println(pl.getProperty("ip"));
			pool = new JedisPool(config, "127.0.0.1");
			// } catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}

		public Jedis getJedis() {
			Jedis jedis = null;
			boolean borrowOrOprSuccess = true;
			try {
				jedis = pool.getResource();
			} catch (JedisConnectionException e) {
				borrowOrOprSuccess = false;
				if (jedis != null)
					pool.returnBrokenResource(jedis);
			} finally {
				if (borrowOrOprSuccess)
					pool.returnResource(jedis);
			}
			jedis = pool.getResource();
			return jedis;
		}

		public JedisPool getJedisPool() {
			return this.pool;
		}

	}

	public static class SerializeUtil {
		public static byte[] serialize(Object object) {
			ObjectOutputStream oos = null;
			ByteArrayOutputStream baos = null;
			try {
				// 序列化
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				byte[] bytes = baos.toByteArray();
				return bytes;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public static Object unserialize(byte[] bytes) {
			if (bytes == null)
				return null;
			ByteArrayInputStream bais = null;
			try {
				// 反序列化
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}