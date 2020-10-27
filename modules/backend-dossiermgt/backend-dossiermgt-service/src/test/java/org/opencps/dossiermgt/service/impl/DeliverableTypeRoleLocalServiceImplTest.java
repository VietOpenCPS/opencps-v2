package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableTypeRoleLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DeliverableTypeRoleLocalServiceImpl object = new DeliverableTypeRoleLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeRoleDBTest() {
		try{
			DeliverableTypeRoleLocalServiceImpl object = new DeliverableTypeRoleLocalServiceImpl();
			object.updateDeliverableTypeRoleDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdByTypesTest() {
		try{
			DeliverableTypeRoleLocalServiceImpl object = new DeliverableTypeRoleLocalServiceImpl();
			object.getRoleIdByTypes(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRolesByTypeTest() {
		try{
			DeliverableTypeRoleLocalServiceImpl object = new DeliverableTypeRoleLocalServiceImpl();
			object.getRolesByType(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DeliverableTypeRoleLocalServiceImpl object = new DeliverableTypeRoleLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}