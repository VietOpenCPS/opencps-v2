package org.opencps.cache.service.impl;

public class RedisCacheManagerImpl {
//implements CacheManager{
//
//	private static final Log logger = LogFactoryUtil.getLog(CacheLocalService.class);
//
//	   //Injecting the Jedis Connection Pool Spring bean.
//	   //Autowire does not work here but we will use Liferay's @BeanReference to inject the bean here.
//	   //The bean definition will be defined later in Spring bean xml.
//	   @BeanReference(name = "jedisPool")
//	   private JedisPool jedisPool;
//	   
//	   @Override
//	   public void addToCache(String cacheName, Serializable key, Serializable value, int ttl) throws PortalException {
//	      logger.debug("Redis Cache: Adding to cache. CacheName = " + cacheName + ", Key = " + key + ", TTL : " + ttl);
//	      Jedis jedis = getResource();
//	      try {
//	         jedis.hset(serialize(cacheName), serialize(key), serialize(value));
//	      } catch (Exception ex) {
//	         logger.error("Redis Cache: Error adding object to cache. CacheName = " + cacheName + ", Key = " + key);
//	      }finally {
//	         jedis.close();
//	      }
//	   }
//
//	   @Override
//	   public Serializable getFromCache(String cacheName, Serializable key) throws PortalException {
//	      logger.debug("Redis Cache: Fetching from cache. CacheName = " + cacheName + ", Key = " + key);
//	      Jedis jedis = getResource();
//	      try {
//	         byte[] result = jedis.hget(serialize(cacheName), serialize(key));
//	         return Optional.ofNullable(result).map(res -> deserialize(res)).orElse(null);
//	      } finally {
//	         jedis.close();
//	      }
//	   }
//
//	   public void removeFromCache(String cacheName, Serializable key) throws PortalException {
//	      logger.debug("Redis Cache: Removing from cache. CacheName = " + cacheName + ", Key = " + key);
//	      Jedis jedis = getResource();
//	      try {
//	         jedis.hdel(serialize(cacheName), serialize(key));
//	      } finally {
//	         jedis.close();
//	      }
//	   }
//
//	   public void clearCache(String cacheName) throws PortalException {
//	      logger.debug("Redis Cache: Clearing cache. CacheName = " + cacheName);
//	      Jedis jedis = getResource();
//	      try {
//	         jedis.del(serialize(cacheName));
//	      } finally {
//	         jedis.close();
//	      }
//	   }
//	   
//	   
//	   private Jedis getResource() throws PortalException {
//	      if(Validator.isNull(jedisPool)) {
//	         logger.error("Redis connection pool is not yet configured.");
//	         throw new PortalException("Redis connection pool is not yet configured.");
//	      }
////	    logger.debug("Redis Cache: Active: " + jedisPool.getNumActive() + ", Idle: " +
////	          jedisPool.getNumIdle() + ", Waiting: " + jedisPool.getNumWaiters());      
//	      return jedisPool.getResource();
//	      
//	   }
//	   
//	   //Serializes an Object to byte array
//	   private byte[] serialize(Serializable obj) {
//	       return SerializationUtils.serialize(obj);
//	   }
//
//	   //De-serialize a byte array back to Object
//	   private Serializable deserialize(byte[] bytes) {
//	       return (Serializable)SerializationUtils.deserialize(bytes);
//	   }
//	   
//	   // Bean Destruction method that will be called on container shutdown.
//	   // Configuration for this will be done in spring xml file
//	   public void destroy() {
//	      if(Validator.isNotNull(jedisPool)) {
//	         logger.debug("Destroying Jedis Connection Pool");
//	         jedisPool.destroy();
//	      }
//	   }
//
//	   @Override
//	   public void closeCachePool() {
//	      destroy();    
//	   }
//
//	   @Override
//	   public void ping() throws PortalException {
//	      try {
//	         Jedis jedis = jedisPool.getResource();
//	         jedis.ping();
//	         jedis.close();
//	      }catch(Exception ex) {
//	         logger.error("Redis Cache : Error pinging redis server. Error = " + ex.getMessage());
//	         throw new PortalException(ex);
//	      }
//	      
//	   }
}
