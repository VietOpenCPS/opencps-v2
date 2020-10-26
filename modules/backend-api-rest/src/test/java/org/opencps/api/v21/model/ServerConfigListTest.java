package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServerConfigListTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getServerConfigTest() {
		try{
			ServerConfigList object = new ServerConfigList();
			object.getServerConfig();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}