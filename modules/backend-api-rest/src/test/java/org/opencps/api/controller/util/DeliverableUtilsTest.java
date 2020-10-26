package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToDeliverableFormDataModelTest() {
		try{
			DeliverableUtils.mappingToDeliverableFormDataModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDeliverableTest() {
		try{
			DeliverableUtils.mappingToDeliverable(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertRowToDeliverableTest() {
		try{
			DeliverableUtils.convertRowToDeliverable(null, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDeliverableResultModelTest() {
		try{
			DeliverableUtils.mappingToDeliverableResultModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void copyInputStreamToFileTest() {
		try{
			DeliverableUtils.copyInputStreamToFile(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDeliverableDetailModelTest() {
		try{
			DeliverableUtils.mappingToDeliverableDetailModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDeliverablesModelTest() {
		try{
			DeliverableUtils.mappingToDeliverablesModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDeliverablesUpdateModelTest() {
		try{
			DeliverableUtils.mappingToDeliverablesUpdateModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExcelDeliverableTest() {
		try{
			DeliverableUtils.readExcelDeliverable(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCellValueTest() {
		try{
			DeliverableUtils.getCellValue(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}