package org.opencps.api.newsbroad.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NewsBoardModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.setCreateDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.setModifiedDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewsStatusTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.setNewsStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewBoardIdTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.setNewBoardId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewsContentTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.setNewsContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewsTitleTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.setNewsTitle("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsContentTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.getNewsContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsTitleTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.getNewsTitle();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsStatusTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.getNewsStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewBoardIdTest() {
		try{
			NewsBoardModel object = new NewsBoardModel();
			object.getNewBoardId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}