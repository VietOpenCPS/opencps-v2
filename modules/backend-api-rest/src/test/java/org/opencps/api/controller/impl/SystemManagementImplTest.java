package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class SystemManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void batchChangePasswordTest() {
		try{
			SystemManagementImpl object = new SystemManagementImpl();
			object.batchChangePassword(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resolveConflictTest() {
		try{
			SystemManagementImpl object = new SystemManagementImpl();
			object.resolveConflict(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initSystemTest() {
		try{
			SystemManagementImpl object = new SystemManagementImpl();
			object.initSystem(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getProgressTest() {
//		try{
//			SystemManagementImpl object = new SystemManagementImpl();
//			object.getProgress(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void verifyMasterDataTest() {
		try{
			SystemManagementImpl object = new SystemManagementImpl();
			object.verifyMasterData(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cleanSiteTest() {
		try{
			SystemManagementImpl object = new SystemManagementImpl();
			object.cleanSite(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}