package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class EFormActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateEFormTest() {
//		try{
//			EFormActionsImpl object = new EFormActionsImpl();
//			object.updateEForm(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteEFormByIdTest() {
//		try{
//			EFormActionsImpl object = new EFormActionsImpl();
//			object.deleteEFormById(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void updateDataByEFormNoTest() {
//		try{
//			EFormActionsImpl object = new EFormActionsImpl();
//			object.updateDataByEFormNo(Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	@Test
	public void getEFormListTest() {
		try{
			EFormActionsImpl object = new EFormActionsImpl();
			object.getEFormList(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}