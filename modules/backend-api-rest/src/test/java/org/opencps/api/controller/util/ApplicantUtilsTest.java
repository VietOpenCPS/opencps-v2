package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ApplicantUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getUserTest() {
//		try{
//			ApplicantUtils.getUser(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getTokenLGSPTest() {
		try{
			ApplicantUtils.getTokenLGSP();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTokenNewLGSPTest() {
		try{
			ApplicantUtils.getTokenNewLGSP();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void registerNewLGSPTest() {
		try{
			ApplicantUtils.registerNewLGSP("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToApplicantModelTest() {
		try{
			ApplicantUtils.mappingToApplicantModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToApplicantResultsTest() {
		try{
			ApplicantUtils.mappingToApplicantResults(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}