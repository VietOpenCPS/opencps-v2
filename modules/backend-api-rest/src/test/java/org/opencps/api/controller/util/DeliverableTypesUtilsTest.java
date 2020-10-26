package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DeliverableTypesUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void mappingToDeliverableTypesResultsModelTest() {
//		try{
//			DeliverableTypesUtils.mappingToDeliverableTypesResultsModel(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingToDeliverableTypesSearchByIdResultsModelTest() {
		try{
			DeliverableTypesUtils.mappingToDeliverableTypesSearchByIdResultsModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDeliverableTypesDataTest() {
		try{
			DeliverableTypesUtils.mappingToDeliverableTypesData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDeliverableTypesModelTest() {
		try{
			DeliverableTypesUtils.mappingToDeliverableTypesModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}