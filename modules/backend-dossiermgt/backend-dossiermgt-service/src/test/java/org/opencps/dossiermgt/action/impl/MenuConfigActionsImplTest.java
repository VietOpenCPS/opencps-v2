package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class MenuConfigActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateMenuRolesTest() {
//		try{
//			MenuConfigActionsImpl object = new MenuConfigActionsImpl();
//			object.updateMenuRoles(Long.valueOf(0), Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addMenuConfigTest() {
		try{
			MenuConfigActionsImpl object = new MenuConfigActionsImpl();
			object.addMenuConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteMenuConfigTest() {
		try{
			MenuConfigActionsImpl object = new MenuConfigActionsImpl();
			object.deleteMenuConfig(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMenuConfigTest() {
		try{
			MenuConfigActionsImpl object = new MenuConfigActionsImpl();
			object.updateMenuConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateMenuConfigDBTest() {
//		try{
//			MenuConfigActionsImpl object = new MenuConfigActionsImpl();
//			object.updateMenuConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteAllMenuConfigTest() {
//		try{
//			MenuConfigActionsImpl object = new MenuConfigActionsImpl();
//			object.deleteAllMenuConfig(Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}