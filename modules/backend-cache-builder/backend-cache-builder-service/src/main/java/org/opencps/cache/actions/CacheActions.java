package org.opencps.cache.actions;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.Serializable;

public interface CacheActions {

	public Serializable getFromCache(String cacheName, Serializable key) throws PortalException;

	// Add to cache
	public void addToCache(String cacheName, Serializable key, Serializable value, int ttl) throws PortalException;

	public void clearCache(String cacheName) throws PortalException;
}
