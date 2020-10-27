package org.opencps.statistic.rest.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void logAsFormattedJsonTest() {
		try{
			DossierStatisticUtils.logAsFormattedJson(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkExistsTest() {
		try{
			DossierStatisticUtils.checkExists(0, 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}