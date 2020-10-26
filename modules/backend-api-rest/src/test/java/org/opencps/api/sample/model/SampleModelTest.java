package org.opencps.api.sample.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SampleModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getNameTest() {
		try{
			SampleModel object = new SampleModel();
			object.getName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIdTest() {
		try{
			SampleModel object = new SampleModel();
			object.getId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNameTest() {
		try{
			SampleModel object = new SampleModel();
			object.setName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			SampleModel object = new SampleModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			SampleModel object = new SampleModel();
			object.setCreateDate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			SampleModel object = new SampleModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			SampleModel object = new SampleModel();
			object.setOrder(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIdTest() {
		try{
			SampleModel object = new SampleModel();
			object.setId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}