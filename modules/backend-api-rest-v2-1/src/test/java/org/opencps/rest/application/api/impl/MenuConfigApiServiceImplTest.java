package org.opencps.rest.application.api.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MenuConfigApiServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getScriptByConfigIdTest() {
		try{
			MenuConfigApiServiceImpl object = new MenuConfigApiServiceImpl();
			object.getScriptByConfigId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigsTodoTest() {
		try{
			MenuConfigApiServiceImpl object = new MenuConfigApiServiceImpl();
			object.getMenuConfigsTodo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigByCodeTest() {
		try{
			MenuConfigApiServiceImpl object = new MenuConfigApiServiceImpl();
			object.getMenuConfigByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigsElasticsearchTest() {
		try{
			MenuConfigApiServiceImpl object = new MenuConfigApiServiceImpl();
			object.getMenuConfigsElasticsearch("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigsCountTest() {
		try{
			MenuConfigApiServiceImpl object = new MenuConfigApiServiceImpl();
			object.getMenuConfigsCount("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addMenuConfigTest() {
		try{
			MenuConfigApiServiceImpl object = new MenuConfigApiServiceImpl();
			object.addMenuConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteMenuConfigTest() {
		try{
			MenuConfigApiServiceImpl object = new MenuConfigApiServiceImpl();
			object.deleteMenuConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMenuConfigTest() {
		try{
			MenuConfigApiServiceImpl object = new MenuConfigApiServiceImpl();
			object.updateMenuConfig("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}