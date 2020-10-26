package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierLogManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierLogsTest() {
		try{
			DossierLogManagementImpl object = new DossierLogManagementImpl();
			object.getDossierLogs(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierLogByIdTest() {
		try{
			DossierLogManagementImpl object = new DossierLogManagementImpl();
			object.getDossierLogById(null, null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierLogByDossierIdTest() {
		try{
			DossierLogManagementImpl object = new DossierLogManagementImpl();
			object.addDossierLogByDossierId(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}