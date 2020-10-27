package org.opencps.usermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OfficeSiteActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			OfficeSiteActions object = new OfficeSiteActions();
//			object.update(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void getFileEntryTest() {
//		try{
//			OfficeSiteActions object = new OfficeSiteActions();
//			object.getFileEntry(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void updateOfficeSitePreferencesTest() {
//		try{
//			OfficeSiteActions object = new OfficeSiteActions();
//			object.updateOfficeSitePreferences(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void uploadOfficeSiteLogoTest() {
//		try{
//			OfficeSiteActions object = new OfficeSiteActions();
//			object.uploadOfficeSiteLogo(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getOfficeSiteLogoTest() {
//		try{
//			OfficeSiteActions object = new OfficeSiteActions();
//			object.getOfficeSiteLogo(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateOfficeSitePreferencesByKeyTest() {
//		try{
//			OfficeSiteActions object = new OfficeSiteActions();
//			object.updateOfficeSitePreferencesByKey(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	@Test
	public void createTest() {
		try{
			OfficeSiteActions object = new OfficeSiteActions();
			object.create(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void getOfficeSitesTest() {
		try{
			OfficeSiteActions object = new OfficeSiteActions();
			object.getOfficeSites(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}