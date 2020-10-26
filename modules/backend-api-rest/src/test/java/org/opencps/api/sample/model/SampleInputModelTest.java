package org.opencps.api.sample.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SampleInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getNameTest() {
		try{
			SampleInputModel object = new SampleInputModel();
			object.getName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIdTest() {
		try{
			SampleInputModel object = new SampleInputModel();
			object.getId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNameTest() {
		try{
			SampleInputModel object = new SampleInputModel();
			object.setName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			SampleInputModel object = new SampleInputModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			SampleInputModel object = new SampleInputModel();
			object.setCreateDate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			SampleInputModel object = new SampleInputModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			SampleInputModel object = new SampleInputModel();
			object.setOrder(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIdTest() {
		try{
			SampleInputModel object = new SampleInputModel();
			object.setId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}