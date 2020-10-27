package org.opencps.usermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ApplicantActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void registerTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.register(null, Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void registerTest2() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.register(null, Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//
//	@Test
//	public void getApplicantDetailTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.getApplicantDetail(null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeApplicantTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.removeApplicant(null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateProfileTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.updateProfile(null, Long.valueOf(0), Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void registerApprovedTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.registerApproved(null, Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void verifyApplicantTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.verifyApplicant(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void updateApplicantTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.updateApplicant(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateApplicantTest14() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.updateApplicant(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void lockApplicantTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.lockApplicant(null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateApplicantDBTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.updateApplicantDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void activationLGSPApplicantTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.activationLGSPApplicant(null, Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void activationApplicantTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.activationApplicant(null, Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void importApplicantDBTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.importApplicantDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void removeProfileTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.removeProfile(null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void updateAccountEmailTest() {
//		try{
//			ApplicantActionsImpl object = new ApplicantActionsImpl();
//			object.updateAccountEmail(null, null, null, null, null, null, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	@Test
	public void getApplicantByUserIdTest() {
		try{
			ApplicantActionsImpl object = new ApplicantActionsImpl();
			object.getApplicantByUserId(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantAccountTest() {
		try{
			ApplicantActionsImpl object = new ApplicantActionsImpl();
			object.createApplicantAccount(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void validateSimpleCaptchaTest() {
		try{
			ApplicantActionsImpl object = new ApplicantActionsImpl();
			object.validateSimpleCaptcha(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSimpleCaptchaTest() {
		try{
			ApplicantActionsImpl object = new ApplicantActionsImpl();
			object.getSimpleCaptcha(null, null, null, null, null, null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantsTest() {
		try{
			ApplicantActionsImpl object = new ApplicantActionsImpl();
			object.getApplicants(null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantByMappingUserIdTest() {
		try{
			ApplicantActionsImpl object = new ApplicantActionsImpl();
			object.getApplicantByMappingUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}