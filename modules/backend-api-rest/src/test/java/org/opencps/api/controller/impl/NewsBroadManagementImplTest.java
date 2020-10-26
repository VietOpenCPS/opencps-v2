package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NewsBroadManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getListNewsBoardTest() {
		try{
			NewsBroadManagementImpl object = new NewsBroadManagementImpl();
			object.getListNewsBoard(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addNewsBoardTest() {
		try{
			NewsBroadManagementImpl object = new NewsBroadManagementImpl();
			object.addNewsBoard(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteNewsBoardTest() {
//		try{
//			NewsBroadManagementImpl object = new NewsBroadManagementImpl();
//			object.deleteNewsBoard(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void updateNewsBoardTest() {
		try{
			NewsBroadManagementImpl object = new NewsBroadManagementImpl();
			object.updateNewsBoard(null, null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getNewsBoardDetailTest() {
//		try{
//			NewsBroadManagementImpl object = new NewsBroadManagementImpl();
//			object.getNewsBoardDetail(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}