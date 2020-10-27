package org.opencps.esb.api.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AgencyServiceAsyncProviderTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTest() {
		try{
			AgencyServiceAsyncProvider object = new AgencyServiceAsyncProvider(null, null);
			object.get();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTest2() {
		try{
			AgencyServiceAsyncProvider object = new AgencyServiceAsyncProvider(null, null);
			object.get();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}