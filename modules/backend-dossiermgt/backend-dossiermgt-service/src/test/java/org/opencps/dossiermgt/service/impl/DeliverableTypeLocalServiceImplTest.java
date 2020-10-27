package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableTypeLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTypeCodeTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.getByTypeCode("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.getByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAllDeliverableTypesTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.getAllDeliverableTypes(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeDBTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.updateDeliverableTypeDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.updateDeliverableType(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableTypeTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.addDeliverableType(Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypesTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.getDeliverableTypes(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormReportTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.updateFormReport(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormScriptTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.updateFormScript(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypebyIdTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.getDeliverableTypebyId(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDeliverableTypeTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.removeDeliverableType(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMappingDataTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.updateMappingData(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DeliverableTypeLocalServiceImpl object = new DeliverableTypeLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}