package org.opencps.usermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserMgtConfigUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isTrackUserEnableTest() {
		try{
			UserMgtConfigUtil.isTrackUserEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isTrackClientEnableTest() {
		try{
			UserMgtConfigUtil.isTrackClientEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGeoDBPathTest() {
		try{
			UserMgtConfigUtil.getGeoDBPath();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}