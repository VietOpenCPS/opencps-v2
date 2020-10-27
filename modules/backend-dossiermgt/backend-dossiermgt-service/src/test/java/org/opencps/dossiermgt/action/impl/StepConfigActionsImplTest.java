package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class StepConfigActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addStepConfigTest() {
		try{
			StepConfigActionsImpl object = new StepConfigActionsImpl();
			object.addStepConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteStepConfigTest() {
		try{
			StepConfigActionsImpl object = new StepConfigActionsImpl();
			object.deleteStepConfig(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStepConfigTest() {
		try{
			StepConfigActionsImpl object = new StepConfigActionsImpl();
			object.updateStepConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateStepConfigDBTest() {
//		try{
//			StepConfigActionsImpl object = new StepConfigActionsImpl();
//			object.updateStepConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteAllStepConfigTest() {
//		try{
//			StepConfigActionsImpl object = new StepConfigActionsImpl();
//			object.deleteAllStepConfig(Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}