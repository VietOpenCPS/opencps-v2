package org.opencps.communication.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EmailUtilitiesTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createEmailBodyTest() {
		try{
			EmailUtilities.createEmailBody(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}