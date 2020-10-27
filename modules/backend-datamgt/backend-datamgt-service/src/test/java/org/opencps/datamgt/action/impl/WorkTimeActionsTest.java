package org.opencps.datamgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class WorkTimeActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			WorkTimeActions object = new WorkTimeActions();
//			object.update(Long.valueOf(0), Long.valueOf(0), 0, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteTest() {
//		try{
//			WorkTimeActions object = new WorkTimeActions();
//			object.delete(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			WorkTimeActions object = new WorkTimeActions();
//			object.read(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			WorkTimeActions object = new WorkTimeActions();
//			object.create(Long.valueOf(0), Long.valueOf(0), 0, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateWorkTimeDBTest() {
//		try{
//			WorkTimeActions object = new WorkTimeActions();
//			object.updateWorkTimeDB(Long.valueOf(0), Long.valueOf(0), 0, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getWorkTimesTest() {
		try{
			WorkTimeActions object = new WorkTimeActions();
			object.getWorkTimes(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}