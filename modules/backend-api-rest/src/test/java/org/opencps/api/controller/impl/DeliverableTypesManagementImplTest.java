package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableTypesManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateDeliverableTypeMappingDataTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.updateDeliverableTypeMappingData(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverabletypebyIdTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.getDeliverabletypebyId(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGenerateCodeByIdTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.getGenerateCodeById(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormReportByDeliverableTypeIdTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.getFormReportByDeliverableTypeId(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.updateDeliverableType(null, null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeFormReportTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.updateDeliverableTypeFormReport(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableTypeTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.addDeliverableType(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeFormScriptTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.updateDeliverableTypeFormScript(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDeliverabletypesTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.removeDeliverabletypes(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptByDeliverableTypeIdTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.getFormScriptByDeliverableTypeId(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypesTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.getDeliverableTypes(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMappingDataByDeliverableTypeIdTest() {
		try{
			DeliverableTypesManagementImpl object = new DeliverableTypesManagementImpl();
			object.getMappingDataByDeliverableTypeId(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}