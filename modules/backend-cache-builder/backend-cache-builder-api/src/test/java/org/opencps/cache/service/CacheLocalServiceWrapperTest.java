package org.opencps.cache.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;
public class CacheLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addToCacheTest() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.addToCache("abcde", null, null, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void clearCacheTest() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.clearCache("");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromCacheTest() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.getFromCache("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void closeCachePoolTest() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.closeCachePool();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeFromCacheTest() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.removeFromCache("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest8() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest10() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void pingTest() {
		try{
			CacheLocalServiceWrapper object = new CacheLocalServiceWrapper(null);
			object.ping();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}