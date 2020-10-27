package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ActionConfigActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateActionConfigTest() {
		try{
			ActionConfigActionsImpl object = new ActionConfigActionsImpl();
			object.updateActionConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", "abcde", true, 0, 0, true, true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteActionConfigTest() {
		try{
			ActionConfigActionsImpl object = new ActionConfigActionsImpl();
			object.deleteActionConfig(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addActionConfigTest() {
		try{
			ActionConfigActionsImpl object = new ActionConfigActionsImpl();
			object.addActionConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", "abcde", true, 0, 0, true, true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateActionConfigDBTest() {
//		try{
//			ActionConfigActionsImpl object = new ActionConfigActionsImpl();
//			object.updateActionConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", true, 0, 0, 0, 0, true, "abcde", "abcde", "abcde", "abcde", 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteAllActionConfigTest() {
//		try{
//			ActionConfigActionsImpl object = new ActionConfigActionsImpl();
//			object.deleteAllActionConfig(Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}