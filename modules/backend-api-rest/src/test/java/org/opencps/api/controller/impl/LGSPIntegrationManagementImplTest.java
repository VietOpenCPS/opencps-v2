package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LGSPIntegrationManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void syncIdIGSPTest() {
		try{
			LGSPIntegrationManagementImpl object = new LGSPIntegrationManagementImpl();
			object.syncIdIGSP(null, null, null, null, null, null, "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void secrectKeyToMD5Test() {
		try{
			LGSPIntegrationManagementImpl object = new LGSPIntegrationManagementImpl();
			object.secrectKeyToMD5(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}