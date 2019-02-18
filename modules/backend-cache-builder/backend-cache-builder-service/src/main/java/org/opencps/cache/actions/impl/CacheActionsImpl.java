package org.opencps.cache.actions.impl;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import org.opencps.cache.actions.CacheActions;

public class CacheActionsImpl implements CacheActions{

	private static final Log _log = LogFactoryUtil.getLog(CacheActionsImpl.class);

	@Override
	public void clearCache(String cacheName) throws PortalException {
		_log.info("Liferay Cache: Clearing cache. CacheName = " + cacheName);
		System.out.println("Liferay Cache: Clearing cache. CacheName = " + cacheName);
		//CacheLocalServiceUtil.clearCache(cacheName);
		if (Validator.isNotNull(cacheName)) {
			try {
				System.out.println("Liferay Cache: Clearing cache. CacheName = " + cacheName);
				PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);
				cache.removeAll();
			} catch (Exception ex) {
				System.out.println("CacheName = " + cacheName + " : Error clearing the cache. Error = " + ex.getMessage());
			}
		} else {
			System.out.println("CacheName = " + cacheName + " : Error clearing the cache. = ");
		}

	}

	@Override
	public Serializable getFromCache(String cacheName, Serializable key) throws PortalException {
		System.out.println("Liferay Cache: Fetching from cache. CacheName = " + cacheName + ", Key = " + key);

		PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);

		Serializable data = cache.get(key);
		if (data != null) {
			return data;
		} else {
			return null;
		}
		
	}

	@Override
	public void addToCache(String cacheName, Serializable key, Serializable value, int ttl) {
		System.out.println("Liferay Cache: Adding to cache. CacheName = " + cacheName + ", Key = " + key + ", TTL : " + ttl);
		PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);
		cache.put(key, value, ttl);
	}

}
