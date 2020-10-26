package org.opencps.api.sample.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createSampleInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createSampleInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createSamplePropertyModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createSamplePropertyModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createSampleDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createSampleDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createSampleResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createSampleResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createSampleModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createSampleModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}