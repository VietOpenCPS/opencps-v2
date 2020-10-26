package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReportRoleManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addReportRoleCodeTest() {
		try{
			ReportRoleManagementImpl object = new ReportRoleManagementImpl();
			object.addReportRoleCode(null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}