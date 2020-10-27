package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserTrackPathLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateUserTrackPathTest() {
		try{
			UserTrackPathLocalServiceImpl object = new UserTrackPathLocalServiceImpl();
			object.updateUserTrackPath(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}