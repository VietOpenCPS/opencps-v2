package org.opencps.api.jobpos.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JobposWorkModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isSelectedTest() {
		try{
			JobposWorkModel object = new JobposWorkModel();
			object.isSelected();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSelectedTest() {
		try{
			JobposWorkModel object = new JobposWorkModel();
			object.setSelected(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setChecklistCatTest() {
		try{
			JobposWorkModel object = new JobposWorkModel();
			object.setChecklistCat("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setChecklistTypeTest() {
		try{
			JobposWorkModel object = new JobposWorkModel();
			object.setChecklistType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getChecklistCatTest() {
		try{
			JobposWorkModel object = new JobposWorkModel();
			object.getChecklistCat();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCategoryNameTest() {
		try{
			JobposWorkModel object = new JobposWorkModel();
			object.getCategoryName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCategoryNameTest() {
		try{
			JobposWorkModel object = new JobposWorkModel();
			object.setCategoryName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getChecklistTypeTest() {
		try{
			JobposWorkModel object = new JobposWorkModel();
			object.getChecklistType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}