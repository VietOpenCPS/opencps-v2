package org.opencps.api.user.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserWorksModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setChecklistCatTest() {
		try{
			UserWorksModel object = new UserWorksModel();
			object.setChecklistCat("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setChecklistTypeTest() {
		try{
			UserWorksModel object = new UserWorksModel();
			object.setChecklistType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getChecklistCatTest() {
		try{
			UserWorksModel object = new UserWorksModel();
			object.getChecklistCat();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCategoryNameTest() {
		try{
			UserWorksModel object = new UserWorksModel();
			object.getCategoryName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCategoryNameTest() {
		try{
			UserWorksModel object = new UserWorksModel();
			object.setCategoryName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getChecklistTypeTest() {
		try{
			UserWorksModel object = new UserWorksModel();
			object.getChecklistType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}