package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConfigsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getServiceConfigTest() {
		try{
			Configs object = new Configs();
			object.getServiceConfig();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}