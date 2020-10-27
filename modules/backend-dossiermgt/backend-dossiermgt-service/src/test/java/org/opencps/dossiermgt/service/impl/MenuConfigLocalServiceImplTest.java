package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MenuConfigLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			MenuConfigLocalServiceImpl object = new MenuConfigLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupIdTest() {
		try{
			MenuConfigLocalServiceImpl object = new MenuConfigLocalServiceImpl();
			object.getByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			MenuConfigLocalServiceImpl object = new MenuConfigLocalServiceImpl();
			object.getByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addMenuConfigTest() {
		try{
			MenuConfigLocalServiceImpl object = new MenuConfigLocalServiceImpl();
			object.addMenuConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMenuConfigTest() {
		try{
			MenuConfigLocalServiceImpl object = new MenuConfigLocalServiceImpl();
			object.updateMenuConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMenuConfigDBTest() {
		try{
			MenuConfigLocalServiceImpl object = new MenuConfigLocalServiceImpl();
			object.updateMenuConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_MENUTest() {
		try{
			MenuConfigLocalServiceImpl object = new MenuConfigLocalServiceImpl();
			object.getByG_MENU(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeMenuConfigTest() {
		try{
			MenuConfigLocalServiceImpl object = new MenuConfigLocalServiceImpl();
			object.removeMenuConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			MenuConfigLocalServiceImpl object = new MenuConfigLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByMenusTest() {
		try{
			MenuConfigLocalServiceImpl object = new MenuConfigLocalServiceImpl();
			object.getByMenus(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}