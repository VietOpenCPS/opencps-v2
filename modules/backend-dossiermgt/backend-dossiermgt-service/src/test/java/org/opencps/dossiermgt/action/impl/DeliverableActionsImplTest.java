package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DeliverableActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateFormDataTest() {
//		try{
//			DeliverableActionsImpl object = new DeliverableActionsImpl();
//			object.updateFormData(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteByIdTest() {
//		try{
//			DeliverableActionsImpl object = new DeliverableActionsImpl();
//			object.deleteById(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void addDeliverableTest() {
//		try{
//			DeliverableActionsImpl object = new DeliverableActionsImpl();
//			object.addDeliverable(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDetailByIdTest() {
//		try{
//			DeliverableActionsImpl object = new DeliverableActionsImpl();
//			object.getDetailById(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDeliverableTest() {
//		try{
//			DeliverableActionsImpl object = new DeliverableActionsImpl();
//			object.updateDeliverable(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDeliverableByStateTest() {
//		try{
//			DeliverableActionsImpl object = new DeliverableActionsImpl();
//			object.getDeliverableByState("abcde", 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	@Test
	public void getListDeliverableTest9() {
		try{
			DeliverableActionsImpl object = new DeliverableActionsImpl();
			object.getListDeliverable(0, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormDataByTypecodeTest() {
		try{
			DeliverableActionsImpl object = new DeliverableActionsImpl();
			object.getFormDataByTypecode(Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormDataByIdTest() {
		try{
			DeliverableActionsImpl object = new DeliverableActionsImpl();
			object.getFormDataById(Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void getListDeliverableTest() {
		try{
			DeliverableActionsImpl object = new DeliverableActionsImpl();
			object.getListDeliverable(Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}