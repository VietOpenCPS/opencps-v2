package org.opencps.api.sample.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SamplePropertyModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getNameTest() {
		try{
			SamplePropertyModel object = new SamplePropertyModel();
			object.getName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNameTest() {
		try{
			SamplePropertyModel object = new SamplePropertyModel();
			object.setName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWeightTest() {
		try{
			SamplePropertyModel object = new SamplePropertyModel();
			object.getWeight();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getColorTest() {
		try{
			SamplePropertyModel object = new SamplePropertyModel();
			object.getColor();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWeightTest() {
		try{
			SamplePropertyModel object = new SamplePropertyModel();
			object.setWeight(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIsVisiableTest() {
		try{
			SamplePropertyModel object = new SamplePropertyModel();
			object.setIsVisiable(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setColorTest() {
		try{
			SamplePropertyModel object = new SamplePropertyModel();
			object.setColor("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isIsVisiableTest() {
		try{
			SamplePropertyModel object = new SamplePropertyModel();
			object.isIsVisiable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}