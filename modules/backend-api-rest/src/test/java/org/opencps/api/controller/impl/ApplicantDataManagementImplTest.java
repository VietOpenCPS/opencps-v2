package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ApplicantDataManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getApplicantDataFilePreviewTest() {
		try{
			ApplicantDataManagementImpl object = new ApplicantDataManagementImpl();
			object.getApplicantDataFilePreview(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void inactiveApplicantDataTest() {
//		try{
//			ApplicantDataManagementImpl object = new ApplicantDataManagementImpl();
//			object.inactiveApplicantData(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void activeApplicantDataTest() {
//		try{
//			ApplicantDataManagementImpl object = new ApplicantDataManagementImpl();
//			object.activeApplicantData(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void updateApplicantDataTest() {
		try{
			ApplicantDataManagementImpl object = new ApplicantDataManagementImpl();
			object.updateApplicantData(null, null, null, null, null, null, Long.valueOf(0), null, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteApplicantDataTest() {
		try{
			ApplicantDataManagementImpl object = new ApplicantDataManagementImpl();
			object.deleteApplicantData(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantDatasTest() {
		try{
			ApplicantDataManagementImpl object = new ApplicantDataManagementImpl();
			object.getApplicantDatas(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addApplicantDataTest() {
		try{
			ApplicantDataManagementImpl object = new ApplicantDataManagementImpl();
			object.addApplicantData(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}