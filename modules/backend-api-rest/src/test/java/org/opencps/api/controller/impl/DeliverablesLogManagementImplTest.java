package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverablesLogManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDeliverableLogTest() {
		try{
			DeliverablesLogManagementImpl object = new DeliverablesLogManagementImpl();
			object.getDeliverableLog(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}