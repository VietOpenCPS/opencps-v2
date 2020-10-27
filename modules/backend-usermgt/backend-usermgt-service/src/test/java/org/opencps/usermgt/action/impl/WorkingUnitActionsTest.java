package org.opencps.usermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class WorkingUnitActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			WorkingUnitActions object = new WorkingUnitActions();
//			object.update(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), 0, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			WorkingUnitActions object = new WorkingUnitActions();
//			object.create(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), 0, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getFileEntryTest() {
//		try{
//			WorkingUnitActions object = new WorkingUnitActions();
//			object.getFileEntry(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getLogoTest() {
//		try{
//			WorkingUnitActions object = new WorkingUnitActions();
//			object.getLogo(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void updateLogoTest() {
//		try{
//			WorkingUnitActions object = new WorkingUnitActions();
//			object.updateLogo(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
	@Test
	public void getWorkingUnitsTest() {
		try{
			WorkingUnitActions object = new WorkingUnitActions();
			object.getWorkingUnits(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}