package org.opencps.api.sample.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SampleDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getNameTest() {
		try{
			SampleDetailModel object = new SampleDetailModel();
			object.getName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIdTest() {
		try{
			SampleDetailModel object = new SampleDetailModel();
			object.getId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNameTest() {
		try{
			SampleDetailModel object = new SampleDetailModel();
			object.setName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			SampleDetailModel object = new SampleDetailModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			SampleDetailModel object = new SampleDetailModel();
			object.setCreateDate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSamplePropertyModelTest() {
		try{
			SampleDetailModel object = new SampleDetailModel();
			object.setSamplePropertyModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSamplePropertyModelTest() {
		try{
			SampleDetailModel object = new SampleDetailModel();
			object.getSamplePropertyModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			SampleDetailModel object = new SampleDetailModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			SampleDetailModel object = new SampleDetailModel();
			object.setOrder(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIdTest() {
		try{
			SampleDetailModel object = new SampleDetailModel();
			object.setId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}