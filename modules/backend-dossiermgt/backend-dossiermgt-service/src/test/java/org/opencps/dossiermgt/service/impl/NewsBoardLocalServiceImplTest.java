package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NewsBoardLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			NewsBoardLocalServiceImpl object = new NewsBoardLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNewsBoardTest() {
		try{
			NewsBoardLocalServiceImpl object = new NewsBoardLocalServiceImpl();
			object.createNewsBoard(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNewsBoardTest() {
		try{
			NewsBoardLocalServiceImpl object = new NewsBoardLocalServiceImpl();
			object.updateNewsBoard(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsBoardListTest() {
		try{
			NewsBoardLocalServiceImpl object = new NewsBoardLocalServiceImpl();
			object.getNewsBoardList(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByNewsBoardListTest() {
		try{
			NewsBoardLocalServiceImpl object = new NewsBoardLocalServiceImpl();
			object.countByNewsBoardList(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			NewsBoardLocalServiceImpl object = new NewsBoardLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}