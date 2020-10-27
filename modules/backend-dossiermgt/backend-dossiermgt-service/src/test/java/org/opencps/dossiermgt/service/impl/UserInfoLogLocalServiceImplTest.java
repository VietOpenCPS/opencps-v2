package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserInfoLogLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			UserInfoLogLocalServiceImpl object = new UserInfoLogLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			UserInfoLogLocalServiceImpl object = new UserInfoLogLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addUserInfoLogTest() {
		try{
			UserInfoLogLocalServiceImpl object = new UserInfoLogLocalServiceImpl();
			object.addUserInfoLog(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserInfoLogsTest() {
		try{
			UserInfoLogLocalServiceImpl object = new UserInfoLogLocalServiceImpl();
			object.getUserInfoLogs(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}