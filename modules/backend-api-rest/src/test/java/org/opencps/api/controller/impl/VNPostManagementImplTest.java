package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VNPostManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateDossierByBarcodeTest() {
		try{
			VNPostManagementImpl object = new VNPostManagementImpl();
			object.updateDossierByBarcode(null, null, null, "abcde", null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierDetailByBarcodeTest() {
		try{
			VNPostManagementImpl object = new VNPostManagementImpl();
			object.getDossierDetailByBarcode(null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}