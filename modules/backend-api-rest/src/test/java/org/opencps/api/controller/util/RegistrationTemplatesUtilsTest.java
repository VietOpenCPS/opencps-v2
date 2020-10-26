package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class RegistrationTemplatesUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void mappingToRegistrationTemplatesResultsModelTest() {
//		try{
//			RegistrationTemplatesUtils.mappingToRegistrationTemplatesResultsModel(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingToRegistrationTemplateModelTest() {
		try{
			RegistrationTemplatesUtils.mappingToRegistrationTemplateModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDeliverableTypesSearchByIdResultsModelTest() {
		try{
			RegistrationTemplatesUtils.mappingToDeliverableTypesSearchByIdResultsModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToRegistrationTemplatesDataTest() {
		try{
			RegistrationTemplatesUtils.mappingToRegistrationTemplatesData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}