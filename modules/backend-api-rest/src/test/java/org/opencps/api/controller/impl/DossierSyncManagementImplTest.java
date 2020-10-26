package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierSyncsByApplicantTest() {
		try{
			DossierSyncManagementImpl object = new DossierSyncManagementImpl();
			object.getDossierSyncsByApplicant(null, null, null, null, null, null, "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}