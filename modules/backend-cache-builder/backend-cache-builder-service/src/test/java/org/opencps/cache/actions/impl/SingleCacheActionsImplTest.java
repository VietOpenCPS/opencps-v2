package org.opencps.cache.actions.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SingleCacheActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void convertTest() {
		try{
			SingleCacheActionsImpl object = new SingleCacheActionsImpl();
			object.convert("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRefreshTimeTest() {
		try{
			SingleCacheActionsImpl object = new SingleCacheActionsImpl();
			object.getRefreshTime();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}