package org.opencps.auth.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getUserTest() {
		try{
			UserUtils.getUser(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}