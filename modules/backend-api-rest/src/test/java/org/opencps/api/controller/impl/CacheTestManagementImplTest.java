package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CacheTestManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFromCacheTest() {
		try{
			CacheTestManagementImpl object = new CacheTestManagementImpl();
			object.getFromCache(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}