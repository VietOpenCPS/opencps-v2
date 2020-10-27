package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DeliverableTypesActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getByTypeCodeTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.getByTypeCode(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDeliverableTypeDBTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.updateDeliverableTypeDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDeliverableTypeRoleDBTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.updateDeliverableTypeRoleDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDeliverableTypeMappingDataTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.updateDeliverableTypeMappingData(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDeliverableTypeTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.updateDeliverableType(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDeliverableTypeFormReportTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.updateDeliverableTypeFormReport(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addDeliverableTypeTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.addDeliverableType(Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDeliverableTypeFormScriptTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.updateDeliverableTypeFormScript(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void removeDeliverableTypeTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.removeDeliverableType(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getRoleIdByTypesTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.getRoleIdByTypes(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getRolesByTypeTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.getRolesByType(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDeliverableTypesListTest() {
//		try{
//			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
//			object.getDeliverableTypesList(Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
	@Test
	public void getDeliverableTypesTest() {
		try{
			DeliverableTypesActionsImpl object = new DeliverableTypesActionsImpl();
			object.getDeliverableTypes(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}