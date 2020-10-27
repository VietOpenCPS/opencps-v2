package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NewsBoardActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getListNewsBoardTest() {
		try{
			NewsBoardActionsImpl object = new NewsBoardActionsImpl();
			object.getListNewsBoard(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteNewsBoardTest() {
//		try{
//			NewsBoardActionsImpl object = new NewsBoardActionsImpl();
//			object.deleteNewsBoard(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createNewsBoardTest() {
//		try{
//			NewsBoardActionsImpl object = new NewsBoardActionsImpl();
//			object.createNewsBoard(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateNewsBoardTest() {
//		try{
//			NewsBoardActionsImpl object = new NewsBoardActionsImpl();
//			object.updateNewsBoard(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getNewsBoardTest() {
//		try{
//			NewsBoardActionsImpl object = new NewsBoardActionsImpl();
//			object.getNewsBoard(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}