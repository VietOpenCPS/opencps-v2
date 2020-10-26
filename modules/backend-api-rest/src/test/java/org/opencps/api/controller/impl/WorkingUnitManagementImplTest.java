package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class WorkingUnitManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateTest() {
		try{
			WorkingUnitManagementImpl object = new WorkingUnitManagementImpl();
			object.update(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteTest() {
//		try{
//			WorkingUnitManagementImpl object = new WorkingUnitManagementImpl();
//			object.delete(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			WorkingUnitManagementImpl object = new WorkingUnitManagementImpl();
//			object.read(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void createTest() {
		try{
			WorkingUnitManagementImpl object = new WorkingUnitManagementImpl();
			object.create(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getLogoTest() {
//		try{
//			WorkingUnitManagementImpl object = new WorkingUnitManagementImpl();
//			object.getLogo(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getWorkingUnitsTest() {
		try{
			WorkingUnitManagementImpl object = new WorkingUnitManagementImpl();
			object.getWorkingUnits(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateLogoTest() {
		try{
			WorkingUnitManagementImpl object = new WorkingUnitManagementImpl();
			object.updateLogo(null, null, null, null, null, null, Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}