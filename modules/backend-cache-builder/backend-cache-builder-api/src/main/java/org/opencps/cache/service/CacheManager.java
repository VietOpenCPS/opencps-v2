package org.opencps.cache.service;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.Serializable;

public interface CacheManager {

	// Add to cache
	public void addToCache(String cacheName, Serializable key, Serializable value, int ttl) throws PortalException;

	// Get from cache
	public Serializable getFromCache(String cacheName, Serializable key) throws PortalException;

	// Remove from cache
	public void removeFromCache(String cacheName, Serializable key) throws PortalException;

	// Clear the entire cache
	public void clearCache(String cacheName) throws PortalException;

	// Close the cache connection pool, if implemented.
	public void closeCachePool();

	// Ping the connection to find if it is working
	public void ping() throws PortalException;

}
