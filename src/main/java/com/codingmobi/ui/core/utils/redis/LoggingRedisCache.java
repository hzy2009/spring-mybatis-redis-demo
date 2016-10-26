package com.codingmobi.ui.core.utils.redis;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;

public class LoggingRedisCache extends LoggingCache {

	public LoggingRedisCache(Cache delegate) {
		super(new RedisCache(delegate));
	}
	
	public LoggingRedisCache(String id) {
		super(new RedisCache(id));
	}
	
	

}
