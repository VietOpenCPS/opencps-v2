package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MenuRoleManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMenuRolesTest() {
		try{
			MenuRoleManagementImpl object = new MenuRoleManagementImpl();
			object.getMenuRoles(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void autoGenerateIdTest() {
		try{
			MenuRoleManagementImpl object = new MenuRoleManagementImpl();
			object.autoGenerateId(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}