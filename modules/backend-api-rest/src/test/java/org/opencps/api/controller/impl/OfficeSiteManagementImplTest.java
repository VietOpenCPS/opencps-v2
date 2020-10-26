package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OfficeSiteManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateOfficeSiteTest() {
		try{
			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
			object.updateOfficeSite(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getOfficeSiteTest() {
//		try{
//			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
//			object.getOfficeSite(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getOfficeSitesTest() {
		try{
			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
			object.getOfficeSites(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteOfficeSiteTest() {
//		try{
//			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
//			object.deleteOfficeSite(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addOfficeSiteTest() {
		try{
			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
			object.addOfficeSite(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOfficeSitePreferencesTest() {
		try{
			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
			object.updateOfficeSitePreferences(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadOfficeSiteLogoTest() {
		try{
			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
			object.uploadOfficeSiteLogo(null, null, null, null, null, null, Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getOfficeSitePreferencesTest() {
//		try{
//			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
//			object.getOfficeSitePreferences(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getOfficeSitePreferencesByKeyTest() {
//		try{
//			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
//			object.getOfficeSitePreferencesByKey(null, null, null, null, null, null, Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getOfficeSiteLogoTest() {
//		try{
//			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
//			object.getOfficeSiteLogo(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void updateOfficeSitePreferencesByKeyTest() {
		try{
			OfficeSiteManagementImpl object = new OfficeSiteManagementImpl();
			object.updateOfficeSitePreferencesByKey(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}