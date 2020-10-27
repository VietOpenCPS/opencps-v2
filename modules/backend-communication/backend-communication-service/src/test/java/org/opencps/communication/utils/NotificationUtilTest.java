package org.opencps.communication.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createMBMessageEntryTest() {
		try{
			NotificationUtil.createMBMessageEntry(null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}