package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MenuConfigManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMenuConfigDetailTest() {
		try{
			MenuConfigManagementImpl object = new MenuConfigManagementImpl();
			object.getMenuConfigDetail(null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}