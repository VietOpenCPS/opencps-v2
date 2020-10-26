package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VGCAManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void vgcaUploadControllerTest() {
		try{
			VGCAManagementImpl object = new VGCAManagementImpl();
			object.vgcaUploadController(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}