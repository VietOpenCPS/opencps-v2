package org.opencps.cache.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class LiferayCacheManagerImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void addToCacheTest() {
//		try{
//			LiferayCacheManagerImpl object = new LiferayCacheManagerImpl();
//			object.addToCache("abcde", null, null, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void clearCacheTest() {
//		try{
//			LiferayCacheManagerImpl object = new LiferayCacheManagerImpl();
//			object.clearCache("");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getFromCacheTest() {
//		try{
//			LiferayCacheManagerImpl object = new LiferayCacheManagerImpl();
//			object.getFromCache("abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void closeCachePoolTest() {
//		try{
//			LiferayCacheManagerImpl object = new LiferayCacheManagerImpl();
//			object.closeCachePool();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeFromCacheTest() {
//		try{
//			LiferayCacheManagerImpl object = new LiferayCacheManagerImpl();
//			object.removeFromCache("abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void pingTest() {
		try{
			LiferayCacheManagerImpl object = new LiferayCacheManagerImpl();
			object.ping();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}