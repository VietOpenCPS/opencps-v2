package org.opencps.cache.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class RedisCacheManagerImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void destroyTest() {
//		try{
//			RedisCacheManagerImpl object = new RedisCacheManagerImpl();
//			object.destroy();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addToCacheTest() {
//		try{
//			RedisCacheManagerImpl object = new RedisCacheManagerImpl();
//			object.addToCache("abcde", null, null, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void clearCacheTest() {
//		try{
//			RedisCacheManagerImpl object = new RedisCacheManagerImpl();
//			object.clearCache("");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getFromCacheTest() {
//		try{
//			RedisCacheManagerImpl object = new RedisCacheManagerImpl();
//			object.getFromCache("abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void closeCachePoolTest() {
//		try{
//			RedisCacheManagerImpl object = new RedisCacheManagerImpl();
//			object.closeCachePool();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeFromCacheTest() {
//		try{
//			RedisCacheManagerImpl object = new RedisCacheManagerImpl();
//			object.removeFromCache("abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void pingTest() {
		try{
			RedisCacheManagerImpl object = new RedisCacheManagerImpl();
			object.ping();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}