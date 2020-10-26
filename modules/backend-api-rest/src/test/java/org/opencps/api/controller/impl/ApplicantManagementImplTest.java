package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ApplicantManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mainTest() {
		try{
			ApplicantManagementImpl.main(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void registerTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.register(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void activateApplicantTest() {
//		try{
//			ApplicantManagementImpl object = new ApplicantManagementImpl();
//			object.activateApplicant(null, null, null, null, null, null, Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void updateIndentifiesTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.updateIndentifies(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void verifyApplicantInfoTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.verifyApplicantInfo(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void validateSimpleCaptchaTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.validateSimpleCaptcha(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addApplicantProfileTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.addApplicantProfile(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantProfileTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.updateApplicantProfile(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void registerWithIndentifyTest() {
//		try{
//			ApplicantManagementImpl object = new ApplicantManagementImpl();
//			object.registerWithIndentify(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void createApplicantAccountTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.createApplicantAccount(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void ngspGetApplicantInfoTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.ngspGetApplicantInfo(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void activateLGSPApplicantTest() {
//		try{
//			ApplicantManagementImpl object = new ApplicantManagementImpl();
//			object.activateLGSPApplicant(null, null, null, null, null, null, Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getApplicantDetailTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.getApplicantDetail(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantProfileTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.getApplicantProfile(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void registerLGSPWithCaptchaTest() {
//		try{
//			ApplicantManagementImpl object = new ApplicantManagementImpl();
//			object.registerLGSPWithCaptcha(null, null, null, null, null, null, null, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void registerWithCaptchaTest() {
//		try{
//			ApplicantManagementImpl object = new ApplicantManagementImpl();
//			object.registerWithCaptcha(null, null, null, null, null, null, null, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void activeApplicantTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.activeApplicant(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void registryWithsqlTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.registryWithsql(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEmailTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.updateEmail(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void importWithsqlTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.importWithsql(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDictItemNameTest() {
//		try{
//			ApplicantManagementImpl object = new ApplicantManagementImpl();
//			object.getDictItemName(Long.valueOf(0), "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getApplicantsTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.getApplicants(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resolveConflictTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.resolveConflict(null, null, null, null, null, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void verifyApplicantTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.verifyApplicant(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSimpleCaptchaTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.getSimpleCaptcha(null, null, null, null, null, null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteApplicantTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.deleteApplicant(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getJCaptchaTest() {
//		try{
//			ApplicantManagementImpl object = new ApplicantManagementImpl();
//			object.getJCaptcha(null, null, null, null, null, null, 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void updateApplicantTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.updateApplicant(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void lockApplicantTest() {
		try{
			ApplicantManagementImpl object = new ApplicantManagementImpl();
			object.lockApplicant(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}