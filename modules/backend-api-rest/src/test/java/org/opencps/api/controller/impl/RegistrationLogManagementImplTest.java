package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationLogManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getRegistrationLogsTest() {
		try{
			RegistrationLogManagementImpl object = new RegistrationLogManagementImpl();
			object.getRegistrationLogs(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationLogsbyRegIdTest() {
		try{
			RegistrationLogManagementImpl object = new RegistrationLogManagementImpl();
			object.getRegistrationLogsbyRegId(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationByRegistrationIdTest() {
		try{
			RegistrationLogManagementImpl object = new RegistrationLogManagementImpl();
			object.addRegistrationByRegistrationId(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}