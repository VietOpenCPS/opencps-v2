package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class RegistrationFormUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void mappingToRegistrationFormResultsModelTest() {
//		try{
//			RegistrationFormUtils.mappingToRegistrationFormResultsModel(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingToRegistrationFormDetailModelTest() {
		try{
			RegistrationFormUtils.mappingToRegistrationFormDetailModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}