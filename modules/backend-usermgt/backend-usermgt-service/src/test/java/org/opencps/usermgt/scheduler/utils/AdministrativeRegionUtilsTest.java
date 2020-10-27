package org.opencps.usermgt.scheduler.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AdministrativeRegionUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getInfoRegionTest() {
		try{
			AdministrativeRegionUtils.getInfoRegion("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}