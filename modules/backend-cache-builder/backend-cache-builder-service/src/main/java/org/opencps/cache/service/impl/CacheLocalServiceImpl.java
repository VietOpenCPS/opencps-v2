/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.cache.service.impl;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import org.opencps.cache.actions.impl.CacheActionsImpl;
import org.opencps.cache.service.CacheLocalService;
import org.opencps.cache.service.CacheManager;
import org.opencps.cache.service.PortalFunction;
import org.opencps.cache.service.base.CacheLocalServiceBaseImpl;

/**
 * The implementation of the cache local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.cache.service.CacheLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CacheLocalServiceBaseImpl
 * @see org.opencps.cache.service.CacheLocalServiceUtil
 */
public class CacheLocalServiceImpl extends CacheLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.cache.service.CacheLocalServiceUtil} to access the cache local service.
	 */


	//private static final Log _log = LogFactoryUtil.getLog(CacheActionsImpl.class);

//	public Serializable getFromCache(String cacheName, Serializable key) {
//		System.out.println("Liferay Cache: Fetching from cache. CacheName = " + cacheName + ", Key = " + key);
//
//		PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);
//		if (cache != null) {
//			return cache.get(key);
//		}
//		else return null;
//	}

//	public void addToCache(String cacheName, Serializable key, Serializable value, int ttl) {
//		System.out.println("Liferay Cache: Adding to cache. CacheName = " + cacheName + ", Key = " + key + ", TTL : " + ttl);
//		PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);
//		cache.put(key, value, ttl);
//	}
//
//	public void clearCache(String cacheName) throws PortalException {
//		_log.info("Liferay Cache: Clearing cache. CacheName = " + cacheName);
//		System.out.println("Liferay Cache: Clearing cache. CacheName = " + cacheName);
//		//CacheLocalServiceUtil.clearCache(cacheName);
//		if (Validator.isNotNull(cacheName)) {
//			try {
//				System.out.println("Liferay Cache: Clearing cache. CacheName = " + cacheName);
//				PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);
//				cache.removeAll();
//			} catch (Exception ex) {
//				System.out.println("CacheName = " + cacheName + " : Error clearing the cache. Error = " + ex.getMessage());
//			}
//		} else {
//			System.out.println("CacheName = " + cacheName + " : Error clearing the cache. = ");
//		}
//	}
//
//	public void removeFromCache(String cacheName, Serializable key) throws PortalException {
//		_log.info("Liferay Cache: Removing from cache. CacheName = " + cacheName + ", Key = " + key);
//		PortalCache<Serializable, Serializable> cache = MultiVMPoolUtil.getPortalCache(cacheName);
//		cache.remove(key);
//
//	}
//
//	public void closeCachePool() {
//		MultiVMPoolUtil.clear();
//	}
//
//	public void ping() throws PortalException {
//		// DO NOTHING.
//	}

}

