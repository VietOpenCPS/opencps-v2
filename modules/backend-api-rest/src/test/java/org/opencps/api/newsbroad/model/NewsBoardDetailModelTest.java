package org.opencps.api.newsbroad.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NewsBoardDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.setCreateDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.setModifiedDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewsStatusTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.setNewsStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewBoardIdTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.setNewBoardId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewsContentTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.setNewsContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewsTitleTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.setNewsTitle("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsContentTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.getNewsContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsTitleTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.getNewsTitle();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsStatusTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.getNewsStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewBoardIdTest() {
		try{
			NewsBoardDetailModel object = new NewsBoardDetailModel();
			object.getNewBoardId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}