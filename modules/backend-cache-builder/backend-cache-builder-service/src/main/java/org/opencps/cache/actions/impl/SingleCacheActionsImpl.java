package org.opencps.cache.actions.impl;

import com.liferay.portal.kernel.webcache.WebCacheException;

import org.opencps.cache.actions.SingleCacheActions;

public class SingleCacheActionsImpl implements SingleCacheActions{

	@Override
	public Object convert(String key) throws WebCacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getRefreshTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Override
//	public Object convert(String key) throws WebCacheException {
//		List<String> fooList = FooUtil.getDataByKey(key);
//		return fooList;
//	}
//
//	@Override
//	public long getRefreshTime() {
//		return _REFRESH_TIME;
//	}
//
//	private static final long _REFRESH_TIME = Time.MINUTE * 30;
}
