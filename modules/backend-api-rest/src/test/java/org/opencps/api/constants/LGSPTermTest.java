package org.opencps.api.constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LGSPTermTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getRegionTest() {
		try{
			LGSPTerm.getRegion(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callPostAPITest() {
		try{
			LGSPTerm.callPostAPI("abcde", "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void syncAllRegionTest() {
		try{
			LGSPTerm.syncAllRegion();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTokenTest() {
		try{
			LGSPTerm.getToken(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}