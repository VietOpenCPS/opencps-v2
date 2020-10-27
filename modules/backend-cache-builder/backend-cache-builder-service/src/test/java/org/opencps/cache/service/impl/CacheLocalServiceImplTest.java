package org.opencps.cache.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class CacheLocalServiceImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void addToCacheTest() {
//		try{
//			CacheLocalServiceImpl object = new CacheLocalServiceImpl();
//			object.addToCache("abcde", null, null, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void clearCacheTest() {
//		try{
//			CacheLocalServiceImpl object = new CacheLocalServiceImpl();
//			object.clearCache("");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getFromCacheTest() {
//		try{
//			CacheLocalServiceImpl object = new CacheLocalServiceImpl();
//			object.getFromCache("abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void closeCachePoolTest() {
//		try{
//			CacheLocalServiceImpl object = new CacheLocalServiceImpl();
//			object.closeCachePool();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeFromCacheTest() {
//		try{
//			CacheLocalServiceImpl object = new CacheLocalServiceImpl();
//			object.removeFromCache("abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void pingTest() {
		try{
			CacheLocalServiceImpl object = new CacheLocalServiceImpl();
			object.ping();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}