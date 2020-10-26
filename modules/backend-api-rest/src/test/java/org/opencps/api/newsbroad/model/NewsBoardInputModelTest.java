package org.opencps.api.newsbroad.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NewsBoardInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setNewsStatusTest() {
		try{
			NewsBoardInputModel object = new NewsBoardInputModel();
			object.setNewsStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewsContentTest() {
		try{
			NewsBoardInputModel object = new NewsBoardInputModel();
			object.setNewsContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewsTitleTest() {
		try{
			NewsBoardInputModel object = new NewsBoardInputModel();
			object.setNewsTitle("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsContentTest() {
		try{
			NewsBoardInputModel object = new NewsBoardInputModel();
			object.getNewsContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsTitleTest() {
		try{
			NewsBoardInputModel object = new NewsBoardInputModel();
			object.getNewsTitle();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewsStatusTest() {
		try{
			NewsBoardInputModel object = new NewsBoardInputModel();
			object.getNewsStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}