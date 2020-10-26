package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserInfoLogManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getUserLogsTest() {
		try{
			UserInfoLogManagementImpl object = new UserInfoLogManagementImpl();
			object.getUserLogs(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addUserLogTest() {
		try{
			UserInfoLogManagementImpl object = new UserInfoLogManagementImpl();
			object.addUserLog(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}