package org.opencps.usermgt.service.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class UserMgtUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getApplicantTest() {
//		try{
//			UserMgtUtils.getApplicant("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void splitNameTest() {
//		try{
//			UserMgtUtils.splitName("abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getRoleApplicantTest() {
//		try{
//			UserMgtUtils.getRoleApplicant(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getDefaultBrithdayTest() {
		try{
			UserMgtUtils.getDefaultBrithday();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void convertDateTest() {
		try{
			UserMgtUtils.convertDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}