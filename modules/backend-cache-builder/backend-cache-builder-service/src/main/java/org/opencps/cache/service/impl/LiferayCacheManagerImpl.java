package org.opencps.cache.service.impl;

public class LiferayCacheManagerImpl {
//implements CacheManager{
//
//	private static final Log logger = LogFactoryUtil.getLog(LiferayCacheManagerImpl.class);
//
//	@Override
//	public void addToCache(String cacheName, Serializable key, Serializable value, int ttl) {
//		logger.debug("Liferay Cache: Adding to cache. CacheName = " + cacheName + ", Key = " + key + ", TTL : " + ttl);
//		PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);
//		cache.put(key, value, ttl);
//	}
//
//	@Override
//	public Serializable getFromCache(String cacheName, Serializable key) {
//		logger.debug("Liferay Cache: Fetching from cache. CacheName = " + cacheName + ", Key = " + key);
//
//		PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);
//		return cache.get(key);
//	}
//
//	public void removeFromCache(String cacheName, Serializable key) throws PortalException {
//		logger.debug("Liferay Cache: Removing from cache. CacheName = " + cacheName + ", Key = " + key);
//		PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);
//		cache.remove(key);
//
//	}
//
//	public void clearCache(String cacheName) throws PortalException {
//		logger.debug("Liferay Cache: Clearing cache. CacheName = " + cacheName);
//		PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);
//		cache.removeAll();
//	}
//
//	@Override
//	public void closeCachePool() {
//		MultiVMPoolUtil.clear();
//	}
//
//	@Override
//	public void ping() throws PortalException {
//		// DO NOTHING.
//
//	}
//
}
