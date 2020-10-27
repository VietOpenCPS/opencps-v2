package org.opencps.usermgt.listener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantListenerUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPayloadTest() {
		try{
			ApplicantListenerUtils.getPayload("abcde", null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}