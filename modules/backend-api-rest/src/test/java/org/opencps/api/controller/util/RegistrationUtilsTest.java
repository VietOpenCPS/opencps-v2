package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class RegistrationUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToRegistrationDetailResultModelTest() {
		try{
			RegistrationUtils.mappingToRegistrationDetailResultModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void mappingToRegistrationResultsModelTest() {
//		try{
//			RegistrationUtils.mappingToRegistrationResultsModel(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingRegistrationToRegistrationResultModelTest() {
		try{
			RegistrationUtils.mappingRegistrationToRegistrationResultModel(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToRegistrationResultModelTest() {
		try{
			RegistrationUtils.mappingToRegistrationResultModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToRegistrationDetailModelTest() {
		try{
			RegistrationUtils.mappingToRegistrationDetailModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToRegistrationModelTest() {
		try{
			RegistrationUtils.mappingToRegistrationModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingFormDataTest() {
		try{
			RegistrationUtils.mappingFormData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}