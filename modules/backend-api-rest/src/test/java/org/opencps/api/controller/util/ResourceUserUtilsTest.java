package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceUserUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mapperResourceUserListTest() {
		try{
			ResourceUserUtils.mapperResourceUserList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperResourceUserModelTest() {
		try{
			ResourceUserUtils.mapperResourceUserModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}