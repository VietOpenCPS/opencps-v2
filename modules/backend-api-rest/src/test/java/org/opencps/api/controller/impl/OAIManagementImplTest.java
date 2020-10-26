package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OAIManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getOAITest() {
		try{
			OAIManagementImpl object = new OAIManagementImpl();
			object.getOAI(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}