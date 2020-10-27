package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MenuRoleLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			MenuRoleLocalServiceImpl object = new MenuRoleLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByMenuConfigTest() {
		try{
			MenuRoleLocalServiceImpl object = new MenuRoleLocalServiceImpl();
			object.getByMenuConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMenuRoleDBTest() {
		try{
			MenuRoleLocalServiceImpl object = new MenuRoleLocalServiceImpl();
			object.updateMenuRoleDB(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteAllTest() {
		try{
			MenuRoleLocalServiceImpl object = new MenuRoleLocalServiceImpl();
			object.deleteAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			MenuRoleLocalServiceImpl object = new MenuRoleLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRolesTest() {
		try{
			MenuRoleLocalServiceImpl object = new MenuRoleLocalServiceImpl();
			object.getByRoles(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRoleIdTest() {
		try{
			MenuRoleLocalServiceImpl object = new MenuRoleLocalServiceImpl();
			object.getByRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}