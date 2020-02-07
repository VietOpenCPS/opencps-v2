package org.opencps.cache.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.cache.service.CacheManager;

public class CacheManagerFactoryBean {

	private static final Log logger = LogFactoryUtil.getLog(CacheManagerFactoryBean.class);

	public static CacheManager create(boolean useRedis, CacheManager liferayCacheManager,
			CacheManager redisCacheManager) throws Exception {
		if (useRedis) {
			if (Validator.isNotNull(redisCacheManager)) {
				// Check if redis connection is available
				try {
					redisCacheManager.ping();

					logger.debug("Using Redis cache manager");
					return redisCacheManager;
				} catch (PortalException ex) {
					logger.debug(ex);
					logger.error("RedisCacheManager not initialized... Falling back on liferay cache manager");
				}
			}
		}
		logger.debug("Using Liferay cache manager");
		return liferayCacheManager;
	}

}
