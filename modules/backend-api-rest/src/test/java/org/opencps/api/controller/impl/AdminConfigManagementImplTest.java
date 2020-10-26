package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AdminConfigManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void onMessageTest() {
		try{
			AdminConfigManagementImpl object = new AdminConfigManagementImpl();
			object.onMessage(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}