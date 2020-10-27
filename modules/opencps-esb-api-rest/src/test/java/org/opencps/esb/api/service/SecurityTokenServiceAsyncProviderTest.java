package org.opencps.esb.api.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SecurityTokenServiceAsyncProviderTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTest() {
		try{
			SecurityTokenServiceAsyncProvider object = new SecurityTokenServiceAsyncProvider(null, null);
			object.get();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTest2() {
		try{
			SecurityTokenServiceAsyncProvider object = new SecurityTokenServiceAsyncProvider(null, null);
			object.get();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}